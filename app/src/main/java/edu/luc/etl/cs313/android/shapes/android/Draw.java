package edu.luc.etl.cs313.android.shapes.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import edu.luc.etl.cs313.android.shapes.model.*;

/**
 * A Visitor for drawing a shape to an Android canvas.
 */
public class Draw implements Visitor<Void> {

    // TODO entirely your job (except onCircle)

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
//        draw shape
//        paint.set
        return null;
    }

    @Override
    public Void onFill(final Fill f) {
        paint.setStyle(Style.FILL);
//        Draw shape
        paint.setStyle(Style.STROKE);
//        Reset
        return null;
    }

    @Override
    public Void onGroup(final Group g) {

        return null;
    }

    @Override
    public Void onLocation(final Location l) {
        canvas.translate(l.getX(), l.getY());
        String shapeName = l.getShape().getClass().getName();
        System.out.println(shapeName);
        switch(shapeName) {
            case "Circle":
                onCircle((Circle) l.getShape());
                break;
            case "Fill":
                onFill((Fill) l.getShape());
                break;
            case "Group":
                onGroup((Group) l.getShape());
                break;
            case "Outline":
                onOutline((Outline) l.getShape());
                break;
            case "Polygon":
                onPolygon((Polygon) l.getShape());
                break;
            case "Rectangle":
                onRectangle((Rectangle) l.getShape());
                break;
        }
        canvas.translate(0, 0);
        return null;
    }

    @Override
    public Void onRectangle(final Rectangle r) {
        canvas.drawRect(0, 0, r.getWidth(), r.getHeight(), this.paint);
        return null;
    }

    @Override
    public Void onOutline(Outline o) {

        return null;
    }

    @Override
    public Void onPolygon(final Polygon s) {

//        canvas.drawLines(s.getPoints(), paint);
        return null;
    }
}
