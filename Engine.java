class Engine extends RollingStock {
    private double _pullingCapacity;

    public Engine(double pullingCapacity, String name, int ID, double weight) {
        super(name, ID, weight);
        _pullingCapacity = pullingCapacity;
    }

    public double getPullingCapacity() {
        return _pullingCapacity;
    }

    @Override
    public String toString() {
        return
                "\n\tPullingCapacity = " + _pullingCapacity +
                        super.toString();
    }
}
