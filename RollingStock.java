abstract class RollingStock {
    private String _name;
    private int _ID;
    private double _weight;

    public RollingStock(String name, int ID, double weight) {
        _name = name;
        _ID = ID;
        _weight = weight;
    }

    public String getName() {
        return _name;
    }

    public int getID() {
        return _ID;
    }

    public double getWeight() {
        return _weight;
    }

    @Override
    public String toString() {
        return
                "\n\tName = " + _name +
                        "\n\tID = " + _ID +
                        "\n\tWeight = " + _weight;
    }
}
