package org.zutjmx.cliente.app.ws;

import com.github.javafaker.Faker;
import org.zutjmx.webapp.jaxws.services.Curso;
import org.zutjmx.webapp.jaxws.services.CursoServicioWs;
import org.zutjmx.webapp.jaxws.services.CursoServicioWsImplService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Faker faker = new Faker();

        CursoServicioWs servicioWs = new CursoServicioWsImplService().getCursoServicioWsImplPort();
        Curso curso = new Curso();
        curso.setNombre(faker.book().title());
        curso.setInstructor(faker.book().author());
        String descripcionCurso = faker.dune().quote();
        if(descripcionCurso.length()>50) {
            curso.setDescripcion(descripcionCurso.substring(0,49));
        } else {
            curso.setDescripcion(descripcionCurso);
        }
        curso.setDuracion(100D);

        Curso cursoRespuesta = servicioWs.guardar(curso);
        System.out.println("Nuevo curso: " + cursoRespuesta.getNombre());

        List<Curso> cursos = servicioWs.listar();
        for (Curso c : cursos) {
            System.out.println(":: " + c.getNombre() + " ::");
        }
    }
}
