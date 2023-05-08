package co.edu.unisabana.siga;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    List<Estudiante> estudianteList;

    public Controller() {
        this.estudianteList = new ArrayList<>();
        estudianteList.add(new Estudiante("Abril", 2105, 4, "Masculino", "medicina"));
    }

    @GetMapping(path = "/estudiantes/todos")
    public List<Estudiante> obtenerEstudiantes() {
        return estudianteList;
    }

    @GetMapping(path = "/estudiantes")
    public List<Estudiante> obtenerstudiantesPorSemestre(@RequestParam int semestre, @RequestParam String genero) {
        List<Estudiante> busqueda = new ArrayList<>();
        for (Estudiante estudiante : estudianteList) {
            if (estudiante.getSemestre() == semestre && estudiante.getGenero().equals(genero)) {
                busqueda.add(estudiante);
            }
        }
        return busqueda;
    }

    @GetMapping(path = "/estudiantes/{facultad}")
    public Estudiante obtenerEstudiantePorFacultad (@RequestParam String facultad) {
        for (Estudiante estudiante : estudianteList) {
            if (estudiante.getFacultad().equals(facultad)) {
                return estudiante;
            }
        }
        return new Estudiante();
    }
    /*
    public List<Estudiante> obtenerEstudiantePorFacultad (@PathVariable String facultad, @RequestParam int cantidad) {
        List<Estudiante> busquedaFac = new ArrayList<>();
        for (Estudiante estudiante : estudianteList) {
            if (estudiante.getFacultad().equals(facultad)){
                busquedaFac.add(estudiante);
            }
        }
        return busquedaFac;
    }
    */
    @GetMapping (path = "/estudiante/{codigo}")
    public Estudiante obtenerEstudiantePorCodigo (@PathVariable int codigo) {
        for (Estudiante estudiante : estudianteList) {
            if (estudiante.getCodigo() == codigo) {
                return estudiante;
            }
        }
        return new Estudiante();
    }

    @PostMapping(path = "/estudiante/crear")
    public String crearEstudiante (@RequestBody Estudiante estudiante) {
        estudiante.setCodigo((int) (Math.random() * 1000));
        estudianteList.add(estudiante);
        return "Estudiante ingresado correctamente";
    }
}