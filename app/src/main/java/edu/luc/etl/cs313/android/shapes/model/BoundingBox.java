package edu.luc.etl.cs313.android.shapes.model;

/**
 * A shape visitor for calculating the bounding box, that is, the smallest
 * rectangle containing the shape. The resulting bounding box is returned as a
 * rectangle at a specific location.
 */
public class BoundingBox implements Visitor<Location> {

    // TODO

    @Override
    public Location onCircle(final Circle c) {
        final int radius = c.getRadius();
        return new Location(-radius, -radius, new Rectangle(2 * radius, 2 * radius));
    }

    @Override
    public Location onFill(final Fill f) {
        return f.getShape().accept(this);
    }

    @Override
    public Location onGroup(final Group g) {
//        TODO
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = 0;
        int maxY = 0;
        for (Shape shape : g.getShapes()){
            Location value=  shape.accept(this);
            maxX = Math.max(maxX, value.getX());
            maxY = Math.max(maxY, value.getY());
            minX = Math.min(minX, value.getX());
            minY = Math.min(minY, value.getY());
        }
        return new Location(minX, minY, new Rectangle(maxX- minX, maxY - minY ));
    }

    @Override
    public Location onLocation(final Location l) {
        return new Location(l.getX(),l.getY(), l.getShape().accept(this).getShape());
    }

    @Override
    public Location onRectangle(final Rectangle r) {
        return new Location(0, 0,  new Rectangle(r.getWidth(), r.getHeight()));
    }

    @Override
    public Location onStrokeColor(final StrokeColor c) {
        return c.getShape().accept(this);
    }

    @Override
    public Location onOutline(final Outline o) {
        return o.getShape().accept(this);
    }

    @Override
    public Location onPolygon(final Polygon s) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = 0;
        int maxY = 0;
        for(Point point: s.getPoints()){
            maxX = Math.max(point.getX(), maxX);
            maxY = Math.max(point.getY(), maxY);
            minY = Math.min(point.getY(), minY);
            minX = Math.min(point.getX(), minX);
        }
        return new Location(minX, minY, new Rectangle(maxX- minX, maxY - minY ));
    }
}
