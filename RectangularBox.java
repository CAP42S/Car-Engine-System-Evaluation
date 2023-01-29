class RectangularBox extends Container{
    private double _height;
    private double _width;
    private double _length;

    public RectangularBox(double thickness, double density, double height,
                          double width, double length) {
        super(thickness, density);
        _height = height;
        _width = width;
        _length = length;
    }

    public double getHeight() {
        return _height;
    }

    public double getWidth() {
        return _width;
    }

    public double getLength() {
        return _length;
    }

    @Override
    public double computeInteriorVol() {
        double volume =
                (_length - 2 * getThickness()) *
                        (_width - 2 * getThickness()) *
                        (_height - 2 * getThickness());

        return volume;
    }

    @Override
    public double computeExteriorVol() {
        double volume = _length * _width * _height;
        return volume;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "\n\t\tHeight = " + _height +
                "\n\t\tWidth = " + _width +
                "\n\t\tLength = " + _length ;
    }
}
