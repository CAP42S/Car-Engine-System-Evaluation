class Cylinder extends Container{
    private double _radius;
    private double _height;

    public Cylinder(double thickness, double density, double radius,
                    double height) {
        super(thickness, density);
        _radius = radius;
        _height = height;
    }

    public double getRadius() {
        return _radius;
    }

    public double getHeight() {
        return _height;
    }

    @Override
    public double computeInteriorVol() {
        double volume =
                Math.PI *
                        (_radius - Math.pow(getThickness(), 2)) *
                        (_height - 2 * getThickness());

        return volume;
    }

    @Override
    public double computeExteriorVol() {
        double volume =
                Math.PI *
                        (Math.pow(_radius, 2)) *
                        _height;

        return volume;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "\n\t\tRadius = " + _radius +
                "\n\t\tHeight = " + _height;
    }
}
