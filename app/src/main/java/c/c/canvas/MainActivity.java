package c.c.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

    public class MyView extends View
    {
        private int width;
        private int height;
        private int density;
        private int margin;
        private int drawAreaWidth;
        private int drawAreaHeight;

        Paint paint;

        public MyView(Context context)
        {
            super(context);

            paint = new Paint();




        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            width = getWidth();
            height = getHeight();
            density = canvas.getDensity();
            Log.d("canvas get h",""+canvas.getHeight());
            Log.d("canvas get w",""+canvas.getWidth());
            Log.d("get h"       ,""+getHeight());
            Log.d("get w"       ,""+getWidth());

            margin = width/10;
            drawAreaHeight = height - 2* margin;
            drawAreaWidth = width - 2* margin;
            plotCoordinateSystem(canvas);

            /**
             * @Test
             */
            //drawScale(canvas,1,2,3,4,5,6,7,8);


//            Log.d("density","d = " + canvas.getDensity() );
//            int radius;
//            radius = 100;
//            Paint paint = new Paint();
//            paint.setStyle(Paint.Style.FILL);
//            paint.setColor(Color.WHITE);
//            canvas.drawPaint(paint);
//            // Use Color.parseColor to define HTML colors
//            paint.setColor(Color.parseColor("#CD5C5C"));
//            canvas.drawCircle(x / 2, y / 2, radius, paint);
//
//            paint.setColor(Color.parseColor("#FFDB24"));
//            canvas.drawRect(1, 10, 150, 200, paint);
//
//            Path p = new Path();
//            RectF rectF = new RectF(300,300,480,480);
//            p.arcTo(rectF,0,90);
//            paint.setColor(Color.parseColor("#669BFF"));
//            canvas.drawPath(p, paint);
//
//            float[] points = new float[200];
//            Random r = new Random();
//            for(int i=0;i<200;i++){
//                points[i] = r.nextInt(300);
//            }
//            paint.setColor(Color.parseColor("#000000"));
//            canvas.drawLines(points,paint);
//
//            PointF mPoint1 = new PointF(x/1.2F, y/1.2F);
//            PointF mPoint2 = new PointF(x/24, y/1.2F);
//            Path myPath1;
//            paint.setAntiAlias(true);
//            paint.setStyle(Paint.Style.STROKE);
//            paint.setStrokeWidth(10);
//            paint.setColor(Color.LTGRAY);
//
//            myPath1 = drawCurve(canvas, paint, mPoint1, mPoint2);
//            canvas.drawPath(myPath1, paint);
//
//
//            paint.setStyle(Paint.Style.STROKE);
//            paint.setStrokeWidth(30);
//            // mpoint1 czerwony
//            paint.setColor(Color.parseColor("#FF0100"));
//            canvas.drawPoint(x / 1.2F, y / 1.2F, paint);
//
//            //mpoint2 pomaranczowy
//            paint.setColor(Color.parseColor("#FF6600"));
//            canvas.drawPoint(x/24, y/1.2F,paint);
//
//            //rozowy moveto
//            paint.setColor(Color.parseColor("#FF00B5"));
//            canvas.drawPoint(63*x/64, y/10,paint);
        }


        private void plotCoordinateSystem(Canvas canvas){
            Paint solid = new Paint();
            solid.setARGB(255, 64, 64,64);
            solid.setTextSize(20);
            Paint triangle = new Paint();
            triangle.setARGB(255, 64, 64,64);
            triangle.setStyle(Paint.Style.FILL);
            Paint dashed = new Paint();
            dashed.setARGB(255, 64, 64,64);
            dashed.setStyle(Paint.Style.STROKE);
            dashed.setPathEffect(new DashPathEffect(new float[] {5,5}, 0));

            float[] axisX = new float[]{0,drawAreaHeight,drawAreaWidth,drawAreaHeight};
            float[] axisY = new float[]{0,0,0,drawAreaHeight};
            float[] helpingX = new float[]{ 1*drawAreaWidth/4,0,1*drawAreaWidth/4,drawAreaHeight,
                                            2*drawAreaWidth/4,0,2*drawAreaWidth/4,drawAreaHeight,
                                            3*drawAreaWidth/4,0,3*drawAreaWidth/4,drawAreaHeight};
            float[] helpingY = new float[]{ 0,1*drawAreaHeight/4,drawAreaWidth,1*drawAreaHeight/4,
                                            0,2*drawAreaHeight/4,drawAreaWidth,2*drawAreaHeight/4,
                                            0,3*drawAreaHeight/4,drawAreaWidth,3*drawAreaHeight/4};

            canvas.drawLines(movePoints(axisX  ,margin,margin), solid);
            canvas.drawLines(movePoints(axisY, margin, margin), solid);
            canvas.drawLines(movePoints(helpingX,margin,margin),dashed);
            canvas.drawLines(movePoints(helpingY,margin,margin),dashed);

            Path axisXTriangle = new Path();
            axisXTriangle.moveTo(margin + drawAreaWidth, margin + drawAreaHeight - 10);
            axisXTriangle.lineTo(margin + drawAreaWidth + 20, margin + drawAreaHeight);
            axisXTriangle.lineTo(margin + drawAreaWidth, margin + drawAreaHeight + 10);
            axisXTriangle.close();

            Path axisYTriangle = new Path();
            axisYTriangle.moveTo(margin + 0 - 10, margin + 0     );
            axisYTriangle.lineTo(margin + 0, margin + 0 - 20);
            axisYTriangle.lineTo(margin + 0 + 10, margin + 0);
            axisYTriangle.close();

            canvas.drawPath(axisXTriangle, triangle);
            canvas.drawPath(axisYTriangle, triangle);

            canvas.drawText("Y", margin / 2, margin, solid);
            canvas.drawText("X",width-margin,height-margin/2,solid);
        }

        private void drawScale(Canvas canvas, float x1,float x2, float x3, float x4, float y1, float y2, float y3, float y4){
            Paint paint = new Paint();
            paint.setARGB(255, 64, 64, 64);
            paint.setTextSize(20);
            canvas.drawText(y4 + "", 5, margin + 1 * drawAreaHeight / 4, paint);
            canvas.drawText(y3 + "", 5, margin + 2 * drawAreaHeight / 4, paint);
            canvas.drawText(y2 + "", 5, margin + 3 * drawAreaHeight / 4, paint);
            canvas.drawText(y1 + "", 5, margin + 4 * drawAreaHeight / 4, paint);

            canvas.drawText(x1 + "", margin + 0 * drawAreaWidth / 4, height - margin/2,paint);
            canvas.drawText(x2 + "", margin + 1 * drawAreaWidth / 4, height - margin/2,paint);
            canvas.drawText(x3 + "", margin + 2 * drawAreaWidth / 4, height - margin/2,paint);
            canvas.drawText(x4 + "", margin + 3 * drawAreaWidth / 4, height - margin/2,paint);


        }

        private float[] movePoints(float[] points,float vectorX,float vectorY){
            int pointsVectorLength = points.length;
            float[] moved = new float[pointsVectorLength];
            for(int i=0;i<pointsVectorLength;i=i+2){
                moved[i]= points[i]+vectorX;
                moved[i+1] = points[i+1]+vectorY;
            }
            return  moved;
        }
    }
}
