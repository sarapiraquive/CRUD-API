package co.edu.unisabana.siga;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    List<Estudiante> estudianteList;

    public Controller() {
        this.estudianteList = new ArrayList<>();
        estudianteList.add(new Estudiante("Abril", 2105, 4, "Masculino", "Medicina"));
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

    @GetMapping(path = "/estudiantes/{facultad}/{cantidad}")
    public List<Estudiante> obtenerEstudiantePorFacultad(@PathVariable String facultad, @PathVariable int cantidad) {
        List<Estudiante> busquedaFac = new ArrayList<>();
        for (Estudiante estudiante : estudianteList) {
            if (estudiante.getFacultad().equals(facultad)) {
                busquedaFac.add(estudiante);
            }
        }
        return busquedaFac.subList(0, cantidad);
    }

    @GetMapping (path = "/estudiante/buscar/{codigo}")
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

    @PutMapping (path = "/estudiante/modificar/{codigo}")
    public String modificarEstudiante (@PathVariable int codigo, @RequestBody Estudiante estudiante) {
        for (int i = 0; i < estudianteList.size(); i++) {
            if (estudianteList.get(i).getCodigo() == codigo) {
                estudianteList.set(i, estudiante);
                estudiante.setCodigo(codigo);
                return "Estudiante modificado correctamente";
            }
        }
        return "No se encontró un estudiante con el código digitado";
    }

    @DeleteMapping(path = "/estudiante/eliminar/{codigo}")
    public String eliminarEstudiantePorCodigo(@PathVariable int codigo) {
        Estudiante estudianteAEliminar = null;
        for (Estudiante estudiante : estudianteList) {
            if (estudiante.getCodigo() == codigo) {
                estudianteAEliminar = estudiante;
                break;
            }
        }
        if (estudianteAEliminar != null) {
            estudianteList.remove(estudianteAEliminar);
            return "Estudiante eliminado correctamente";
        } else {
            return "No se encontró un estudiante con el código digitado";
        }
    }

}