package co.edu.unisabana.siga;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    List<Estudiante> estudianteList = new ArrayList<>();

    @GetMapping(path = "/estudiantes/todos")
    public List<Estudiante> obtenerEstudiantes() {
        return estudianteList;
    }
    @GetMapping(path = "/estudiantes")
    public List<Estudiante> obtenerstudiantesPorSemestre(@RequestParam int semestre,
        @RequestParam String genero) {
        List<Estudiante> busqueda = new ArrayList<>();
        for (Estudiante estudiante : estudianteList) {
            if (estudiante.getSemestre() == semestre && estudiante.getGenero().equals(genero)) {
                busqueda.add(estudiante);
            }
        }
        return busqueda;
    }
    @GetMapping (path = " /estudiante/{codigo}")
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