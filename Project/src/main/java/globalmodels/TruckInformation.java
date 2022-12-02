package globalmodels;

import server.models.Informative;

import java.util.List;

public class TruckInformation implements Informative {
    private String status;
    private List<Cargo> cargoes;
    private boolean priority;
    private String name;
    private boolean offense;
    private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOffense() {
        return offense;
    }

    public void setOffense(boolean offense) {
        this.offense = offense;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Cargo> getCargoes() {
        return cargoes;
    }

    public void setCargoes(List<Cargo> cargoes) {
        this.cargoes = cargoes;
    }

    public void clearCargoes() {
        this.cargoes.clear();
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    @Override
    public String getInfo() {
        StringBuilder builder = new StringBuilder()
                .append("Имя: ")
                .append(name)
                .append(",\t")
                .append("статус: ")
                .append(status)
                .append(",\t")
                .append(priority ? "приоритетный" : "неприоритетный")
                .append(",\t")
                .append("нарушения: ")
                .append(offense ? "присутсвуют" : "отсутсвуют")
                .append('\n');
        for (int i = 0; i < cargoes.size(); i++) {
            Cargo cargo = cargoes.get(i);
            builder.append(String.format("Груз %d: %s\n", i + 1, cargo.toString()));
        }
        return builder.toString();
    }



}
