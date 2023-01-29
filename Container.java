abstract class Container {
    private double _thickness;
    private double _density;

    public Container(double thickness, double density) {
        _thickness = thickness;
        _density = density;
    }

    public double getThickness() {
        return _thickness;
    }

    public double getDensity() {
        return _density;
    }

    public abstract double computeInteriorVol();

    public abstract double computeExteriorVol();

    public double computeWeightWalls() {
        double volume = computeExteriorVol() - computeInteriorVol();
        double weight = _density * volume;

        return weight;
    }

    @Override
    public String toString() {
        return
                "\n\t\tThickness = " + _thickness +
                        "\n\t\tDensity = " + _density;
    }
}
