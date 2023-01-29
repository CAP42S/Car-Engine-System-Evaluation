class Contents {
    private String _name;
    private double _density;
    private double _value;

    public Contents(String name, double density, double value) {
        _name = name;
        _density = density;
        _value = value;
    }

    public String getName() {
        return _name;
    }

    public double getDensity() {
        return _density;
    }

    public double getValue() {
        return _value;
    }

    @Override
    public String toString() {
        return
                "\n\t\tContent = " + _name +
                        "\n\t\tDensity = " + _density +
                        "\n\t\tValue = " + _value;
    }
}
