class FreightCar extends RollingStock {
    private double _loadFactor;
    private Contents _contents;
    private Container _container;

    public FreightCar(String name, int ID, double weight, double loadFactor,
                      Contents contents, Container container) {
        super(name, ID, weight);
        _loadFactor = loadFactor;
        _contents = contents;
        _container = container;
    }

    public double getLoadFactor() {
        return _loadFactor;
    }

    public void setLoadFactor(double loadFactor) {
        _loadFactor = loadFactor;
    }

    public double computeWeight() {
        double totalWeight =
                getWeight() +
                        _container.computeWeightWalls() +
                        weightContents();

        return totalWeight;
    }
    private double weightContents() {
        double weightContents =
                _container.computeInteriorVol() *
                        _loadFactor *
                        _contents.getDensity();
        return weightContents;
    }

    public double computeValue() {
        double totalValue = weightContents() *
                _contents.getValue();

        return totalValue;
    }

    @Override
    public String toString() {
        return
                super.toString() +
                        "\n\tLoadFactor = " + _loadFactor +
                        "\n\tContents : " + _contents +
                        "\n\tContainer : " + _container ;
    }
}
