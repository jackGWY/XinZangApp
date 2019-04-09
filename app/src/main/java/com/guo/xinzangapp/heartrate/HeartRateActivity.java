package com.guo.xinzangapp.heartrate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.guo.xinzangapp.R;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class HeartRateActivity extends AppCompatActivity {

    //����
    private Timer timer = new Timer();
    //Timer������Timer����ʹ��
    private TimerTask task;
    private static int gx;
    private static int j;

    private static double flag = 1;
    private Handler handler;
    private String title = "pulse";
    private XYSeries series;
    private XYMultipleSeriesDataset mDataset;
    private GraphicalView chart;
    private XYMultipleSeriesRenderer renderer;
    private Context context;
    private int addX = -1;
    double addY;
    int[] xv = new int[300];
    int[] yv = new int[300];
    int[] hua=new int[]{9,10,11,12,13,14,13,12,11,10,9,8,7,6,7,8,9,10,11,10,10};

    private static final AtomicBoolean processing = new AtomicBoolean(false);
    //Android�ֻ�Ԥ���ؼ�
    private static SurfaceView preview = null;
    //Ԥ��������Ϣ
    private static SurfaceHolder previewHolder = null;
    //Android�ֻ�������
    private static Camera camera = null;
    //private static View image = null;
    private static TextView mTV_Heart_Rate = null;
    private static TextView mTV_Avg_Pixel_Values = null;
    private static TextView mTV_pulse = null;
    private static WakeLock wakeLock = null;
    private static int averageIndex = 0;
    private static final int averageArraySize = 4;
    private static final int[] averageArray = new int[averageArraySize];

    /**
     * ����ö��
     */
    public static enum TYPE {
        GREEN, RED
    };


    private static TYPE currentType = TYPE.GREEN;

    public static TYPE getCurrent() {
        return currentType;
    }

    private static int beatsIndex = 0;

    private static final int beatsArraySize = 3;

    private static final int[] beatsArray = new int[beatsArraySize];

    private static double beats = 0;

    private static long startTime = 0;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);

        initConfig();
    }

    @SuppressWarnings("deprecation")
    private void initConfig() {

        context = getApplicationContext();

        LinearLayout layout = (LinearLayout)findViewById(R.id.id_linearLayout_graph);

        //������������������ϵ����е㣬��һ����ļ��ϣ�������Щ�㻭������
        series = new XYSeries(title);

        //����һ�����ݼ���ʵ����������ݼ�������������ͼ��
        mDataset = new XYMultipleSeriesDataset();

        //���㼯��ӵ�������ݼ���
        mDataset.addSeries(series);

        //���¶������ߵ���ʽ�����Եȵȵ����ã�renderer�൱��һ��������ͼ������Ⱦ�ľ��
        int color = Color.GREEN;
        PointStyle style = PointStyle.CIRCLE;
        renderer = buildRenderer(color, style, true);

        //���ú�ͼ�����ʽ
        setChartSettings(renderer, "X", "Y", 0, 300, 4, 16, Color.WHITE, Color.WHITE);

        //����ͼ��
        chart = ChartFactory.getLineChartView(context, mDataset, renderer);

        //��ͼ����ӵ�������ȥ
        layout.addView(chart, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

        //�����Handlerʵ������������Timerʵ������ɶ�ʱ����ͼ��Ĺ���
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //ˢ��ͼ��
                updateChart();
                super.handleMessage(msg);
            }
        };

        task = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };

        timer.schedule(task, 1,20);           //����
        //��ȡSurfaceView�ؼ�
        preview = (SurfaceView) findViewById(R.id.id_preview);
        previewHolder = preview.getHolder();
        previewHolder.addCallback(surfaceCallback);
        previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        mTV_Heart_Rate = (TextView) findViewById(R.id.id_tv_heart_rate);
        mTV_Avg_Pixel_Values = (TextView) findViewById(R.id.id_tv_Avg_Pixel_Values);
        mTV_pulse = (TextView) findViewById(R.id.id_tv_pulse);

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "DoNotDimScreen");
    }

    //	����
    @Override
    public void onDestroy() {
        //����������ʱ�ص�Timer
        timer.cancel();
        super.onDestroy();
    };

    /**
     * ����ͼ��
     */
    protected XYMultipleSeriesRenderer buildRenderer(int color, PointStyle style, boolean fill) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();

        //����ͼ�������߱������ʽ��������ɫ����Ĵ�С�Լ��ߵĴ�ϸ��
        XYSeriesRenderer r = new XYSeriesRenderer();
        r.setColor(Color.RED);
        r.setLineWidth(1);
        renderer.addSeriesRenderer(r);
        return renderer;
    }

    /**
     * ����ͼ�����ʽ
     * @param renderer
     * @param xTitle��x����
     * @param yTitle��y����
     * @param xMin��x��С����
     * @param xMax��x��󳤶�
     * @param yMin:y��С����
     * @param yMax��y��󳤶�
     * @param axesColor����ɫ
     * @param labelsColor����ǩ
     */
    protected void setChartSettings(XYMultipleSeriesRenderer renderer, String xTitle, String yTitle,
                                    double xMin, double xMax, double yMin, double yMax, int axesColor, int labelsColor) {
        //�йض�ͼ�����Ⱦ�ɲο�api�ĵ�
        renderer.setChartTitle(title);
        renderer.setXTitle(xTitle);
        renderer.setYTitle(yTitle);
        renderer.setXAxisMin(xMin);
        renderer.setXAxisMax(xMax);
        renderer.setYAxisMin(yMin);
        renderer.setYAxisMax(yMax);
        renderer.setAxesColor(axesColor);
        renderer.setLabelsColor(labelsColor);
        renderer.setShowGrid(true);
        renderer.setGridColor(Color.GREEN);
        renderer.setXLabels(20);
        renderer.setYLabels(10);
        renderer.setXTitle("Time");
        renderer.setYTitle("mmHg");
        renderer.setYLabelsAlign(Align.RIGHT);
        renderer.setPointSize((float) 3 );
        renderer.setShowLegend(false);
    }

    /**
     * ����ͼ����Ϣ
     */
    private void updateChart() {
        //���ú���һ����Ҫ���ӵĽڵ�
        if(flag == 1) {
            addY = 10;
        }
        else {
            flag = 1;
            if(gx < 200){
                if(hua[20] > 1){
                    Toast.makeText(HeartRateActivity.this, "请用您的指尖盖住摄像头镜头！", Toast.LENGTH_SHORT).show();
                    hua[20] = 0;
                }
                hua[20]++;
                return;
            }
            else {
                hua[20] = 10;
            }
            j = 0;
        }
        if(j < 20){
            addY=hua[j];
            j++;
        }

        //�Ƴ����ݼ��оɵĵ㼯
        mDataset.removeSeries(series);

        //�жϵ�ǰ�㼯�е����ж��ٵ㣬��Ϊ��Ļ�ܹ�ֻ������100�������Ե���������100ʱ��������Զ��100
        int length = series.getItemCount();
        int bz = 0;
        //addX = length;
        if (length > 300) {
            length = 300;
            bz=1;
        }
        addX = length;
        //���ɵĵ㼯��x��y����ֵȡ��������backup�У����ҽ�x��ֵ��1�������������ƽ�Ƶ�Ч��
        for (int i = 0; i < length; i++) {
            xv[i] = (int) series.getX(i) - bz;
            yv[i] = (int) series.getY(i);
        }

        //�㼯����գ�Ϊ�������µĵ㼯��׼��
        series.clear();
        mDataset.addSeries(series);
        //���²����ĵ����ȼ��뵽�㼯�У�Ȼ����ѭ�����н�����任���һϵ�е㶼���¼��뵽�㼯��
        //�����������һ�°�˳��ߵ�������ʲôЧ������������ѭ���壬������²����ĵ�
        series.add(addX, addY);
        for (int k = 0; k < length; k++) {
            series.add(xv[k], yv[k]);
        }
        //�����ݼ�������µĵ㼯
        //mDataset.addSeries(series);

        //��ͼ���£�û����һ�������߲�����ֶ�̬
        //����ڷ�UI���߳��У���Ҫ����postInvalidate()������ο�api
        chart.invalidate();
    } //����


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onResume() {
        super.onResume();
        wakeLock.acquire();
        try {

            camera = Camera.open();
        }
        catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(HeartRateActivity.this, "请设置摄像头权限！" , Toast.LENGTH_LONG).show();
            //DkToast.makeDkToast(HeartRateActivity.this,"请设置摄像头权限！",100000).show();
        }
        startTime = System.currentTimeMillis();
    }

    @Override
    public void onPause() {
        super.onPause();
        wakeLock.release();
        camera.setPreviewCallback(null);
        camera.stopPreview();
        camera.release();
        camera = null;
    }


    /**
     * ���Ԥ������
     * ���������ʵ�ֶ�̬���½���UI�Ĺ��ܣ�
     * ͨ����ȡ�ֻ�����ͷ�Ĳ�����ʵʱ��̬����ƽ������ֵ�����������Ӷ�ʵʱ��̬��������ֵ��
     */
    private static PreviewCallback previewCallback = new PreviewCallback() {
        public void onPreviewFrame(byte[] data, Camera cam) {
            if (data == null) {
                throw new NullPointerException();
            }
            Camera.Size size = cam.getParameters().getPreviewSize();
            if (size == null) {
                throw new NullPointerException();
            }
            if (!processing.compareAndSet(false, true)) {
                return;
            }
            int width = size.width;
            int height = size.height;

            //ͼ����
            int imgAvg = ImageProcessing.decodeYUV420SPtoRedAvg(data.clone(),height,width);
            gx = imgAvg;
            mTV_Avg_Pixel_Values.setText("平均像素值为" + String.valueOf(imgAvg));

            if (imgAvg == 0 || imgAvg == 255) {
                processing.set(false);
                return;
            }
            //����ƽ��ֵ
            int averageArrayAvg = 0;
            int averageArrayCnt = 0;
            for (int i = 0; i < averageArray.length; i++) {
                if (averageArray[i] > 0) {
                    averageArrayAvg += averageArray[i];
                    averageArrayCnt++;
                }
            }

            //����ƽ��ֵ
            int rollingAverage = (averageArrayCnt > 0)?(averageArrayAvg/averageArrayCnt):0;
            TYPE newType = currentType;
            if (imgAvg < rollingAverage) {
                newType = TYPE.RED;
                if (newType != currentType) {
                    beats++;
                    flag=0;
                    mTV_pulse.setText("脉冲数是" + String.valueOf(beats));
                }
            } else if (imgAvg > rollingAverage) {
                newType = TYPE.GREEN;
            }

            if(averageIndex == averageArraySize) {
                averageIndex = 0;
            }
            averageArray[averageIndex] = imgAvg;
            averageIndex++;

            if (newType != currentType) {
                currentType = newType;
            }

            //��ȡϵͳ����ʱ�䣨ms��
            long endTime = System.currentTimeMillis();
            double totalTimeInSecs = (endTime - startTime) / 1000d;
            if (totalTimeInSecs >= 2) {
                double bps = (beats / totalTimeInSecs);
                int dpm = (int) (bps * 60d);
                if (dpm < 30 || dpm > 180|| imgAvg < 200) {
                    //��ȡϵͳ��ʼʱ�䣨ms��
                    startTime = System.currentTimeMillis();
                    //beats��������
                    beats = 0;
                    processing.set(false);
                    return;
                }

                if(beatsIndex == beatsArraySize) {
                    beatsIndex = 0;
                }
                beatsArray[beatsIndex] = dpm;
                beatsIndex++;

                int beatsArrayAvg = 0;
                int beatsArrayCnt = 0;
                for (int i = 0; i < beatsArray.length; i++) {
                    if (beatsArray[i] > 0) {
                        beatsArrayAvg += beatsArray[i];
                        beatsArrayCnt++;
                    }
                }
                int beatsAvg = (beatsArrayAvg / beatsArrayCnt);
                mTV_Heart_Rate.setText("你的心率是"+String.valueOf(beatsAvg));
                //��ȡϵͳʱ�䣨ms��
                startTime = System.currentTimeMillis();
                beats = 0;
            }
            processing.set(false);
        }
    };

    /**
     * Ԥ���ص��ӿ�
     */
    private static SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {
        //����ʱ����
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera.setPreviewDisplay(previewHolder);
                camera.setPreviewCallback(previewCallback);
            } catch (Throwable t) {
                Log.e("surfaceCallback","Exception in setPreviewDisplay()", t);
            }
        }

        //��Ԥ���ı��ʱ��ص��˷���
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            Camera.Size size = getSmallestPreviewSize(width, height, parameters);
            if (size != null) {
                parameters.setPreviewSize(size.width, size.height);
            }
            camera.setParameters(parameters);
            camera.startPreview();
        }

        //���ٵ�ʱ�����
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    };

    /**
     * ��ȡ�����С��Ԥ���ߴ�
     */
    private static Camera.Size getSmallestPreviewSize(int width, int height, Camera.Parameters parameters) {
        Camera.Size result = null;
        for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
            if (size.width <= width && size.height <= height) {
                if (result == null) {
                    result = size;
                }
                else {
                    int resultArea = result.width * result.height;
                    int newArea = size.width * size.height;
                    if (newArea < resultArea) {
                        result = size;
                    }
                }
            }
        }
        return result;
    }
}
