package com.example.myarc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

class MyView extends View { //View를 상속바다 클래스 MyView를 작성한다.
    private Paint mPaints, mFramePaint; //Paint 객체의 mPaint와 mFramePaint를 선언한다.
    private RectF mBigOval; //사각형 mBigOval를 선언한다.
    private float mStart, mSweep; //시작 각도와 늘어나는 각도를 선언한다.

    private static final float SWEEP_INC = 2; //2도씩 늘어나고
    private static final float START_INC = 15; //15도 부터 시작한다.
//시간이 많이 걸리는 작업은 생성자에서 미리 해둔다.
    public MyView(Context context){ //MyView 생성자가 Context 객체를 생성한다.
        super(context); //context 정보를 상위 클래스로 전달한다.

        mPaints = new Paint(); //paint를 생성한다.
        mPaints.setAntiAlias(true); //안티에인징으로 표면을 매끄럽게한다.
        mPaints.setStyle(Paint.Style.FILL); //꽉 채워서 색칠한다.
        mPaints.setColor(0x88FF0000); //빨강색으로 색칠한다.

        mFramePaint = new Paint(); //paint를 생성한다.
        mFramePaint.setStyle(Paint.Style.STROKE); //선만 그린다.
        mFramePaint.setStrokeWidth(3); //선의 두께는 3으로한다.
        mFramePaint.setColor(0x8800FF00); //색은 연두색으로 한다.
        mBigOval = new RectF(40, 10, 900, 1000); //사각형을 그린다.
    }

    @Override
    protected void onDraw(Canvas canvas){ //생성자 onDraw가 canvas 객체를 생성한다.
        canvas.drawColor(Color.YELLOW); //캔버스 색을 노랑색으로 한다.
        canvas.drawRect(mBigOval, mFramePaint); //사각형은 mBigOval과 mFramePaint 객체의 정보를 받아서 그린다.
        canvas.drawArc(mBigOval, mStart, mSweep, false, mPaints);
        // mPaint로 mStart 각도에서 mSweep 만큼의 원호를 그린다.
        mSweep += SWEEP_INC; //Sweep_INC가 증가하며 각도를 그린다.
        if(mSweep > 360) { //커지는 각도가 360도 보다 크면 한바퀴 돌았기 때문에
            mSweep -= 360; //각도를 다시 -360하여 초기화한다.
            mStart += START_INC; //mStart 값을 지정한다.
            if (mStart >= 360){ //mStart 값이 360도 넘으면
                mStart -= 360; //다시 초기화한다.
            }
        }
        invalidate(); //다시 onDraw가 호출되게 한다.
    }
}
public class MainActivity extends AppCompatActivity { //AppCompatActivity를 상속받아 MainActivity클래스를 작성한다.

    @Override
    protected void onCreate(Bundle savedInstanceState) { //onCreat 메소드의 매개변수를 이전 실행상태를 전달한다.
        super.onCreate(savedInstanceState); //부모의 onCreate를 호출한다.
        setContentView(new MyView(this)); //myView를 액티비티 화먼으로 한다.
    }
}
