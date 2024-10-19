package edu.luc.etl.cs313.android.shapes.android;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import edu.luc.etl.cs313.android.shapes.model.*;

/**
 * A Visitor for drawing a shape to an Android canvas.
 */
public class Draw implements Visitor<Void> {

    // TODO

    private final Canvas canvas;

    private final Paint paint;

    public Draw(final Canvas canvas, final Paint paint) {
        this.canvas = canvas;
        this.paint = paint;
        paint.setStyle(Style.STROKE);
    }

    @Override
    public Void onCircle(final Circle c) {
        canvas.drawCircle(0, 0, c.getRadius(), paint);
        return null;
    }

    @Override
    public Void onStrokeColor(final StrokeColor c) {
        paint.setColor(c.getColor());
        c.getShape().accept(this);
        paint.setColor(Color.BLACK);
        return null;
    }

    @Override
    public Void onFill(final Fill f) {
        paint.setStyle(Style.FILL);
        f.getShape().accept(this);
        paint.setStyle(Style.STROKE);
        return null;
    }

    @Override
    public Void onGroup(final Group g) {
//        TODO
        for(Shape shape : g.getShapes()){
            shape.accept(this);
        }
        return null;
    }

    @Override
    public Void onLocation(final Location l) {
        canvas.translate(l.getX(), l.getY());
        l.getShape().accept(this);
        canvas.translate(-l.getX(), -l.getY());
        return null;
    }

    @Override
    public Void onRectangle(final Rectangle r) {
        canvas.drawRect(0, 0, r.getWidth(), r.getHeight(), this.paint);
        return null;
    }

    @Override
    public Void onOutline(Outline o) {
        paint.setStyle(Style.STROKE);
        o.getShape().accept(this);
        return null;
    }

    @Override
    public Void onPolygon(final Polygon s) {
        for(int i =0; i < s.getPoints().size() -1 ; i++){
            canvas.drawLine(s.getPoints().get(i).getX(), s.getPoints().get(i).getY(), s.getPoints().get(i + 1).getX(), s.getPoints().get(i + 1).getY(), paint);
        }
        return null;
    }
}
