package globalmodels;

public class Cargo {
    private Double weight;
    private String type;

    public Double getWeight() {

        return weight;
    }

    public boolean setWeight(Double weight) {
        if (weight <= 0) {
            return false;
        }
        this.weight = weight;
        return true;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "weight=" + weight +
                ", type='" + type + '\'' +
                '}';
    }
}
