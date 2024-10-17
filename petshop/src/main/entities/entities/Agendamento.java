package entities;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

public class Agendamento {
    private Date data;
    private String horario;

    public Agendamento(Date data, String horario) {
        this.data = data;
        this.horario = horario;
    }

    public Agendamento(Animal animal, Servico servicoObj, LocalDate data, LocalTime parse) {
    }

    public Date getData() {
        return data;
    }

    public String getHorario() {
        return horario;
    }
}
