package entities;

import java.sql.Date;
import java.sql.Time;

public class Agendamento {
    private Date data;
    private Time horario;

    public Agendamento(Date data, Time horario) {
        this.data = data;
        this.horario = horario;
    }

    public Date getData() {
        return data;
    }

    public Time getHorario() {
        return horario;
    }
}
