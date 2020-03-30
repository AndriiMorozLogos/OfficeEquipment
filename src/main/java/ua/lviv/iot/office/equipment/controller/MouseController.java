package ua.lviv.iot.office.equipment.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.office.equipment.model.Mouse;

@RequestMapping("/mouses")
@RestController
public class MouseController {

  private Map<Integer, Mouse> mouses = new HashMap<>();

  private AtomicInteger idCounter = new AtomicInteger();

  @GetMapping
  public List<Mouse> getMouses() {
    return new LinkedList<Mouse>(mouses.values());
  }

  @GetMapping(path = {"/{id}"})
  public ResponseEntity<Mouse> getMouse(final @PathVariable("id") Integer mouseId) {

    Mouse mouse;
    ResponseEntity<Mouse> status = (mouse = mouses.get(mouseId)) == null
        ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
        : new ResponseEntity<>(mouse, HttpStatus.OK);
    return status;

  }

  @PostMapping
  public Mouse createMouse(final @RequestBody Mouse mouse) {
    mouse.setId(idCounter.incrementAndGet());
    mouses.put(mouse.getId(), mouse);
    return mouse;
  }

  @DeleteMapping(path = {"/{id}"})
  public ResponseEntity<Mouse> deleteMouse(@PathVariable("id") Integer mouseId) {
    HttpStatus status = mouses.remove(mouseId) == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;

    return ResponseEntity.status(status).build();
  }

  @PutMapping(path = {"/{id}"})
  public ResponseEntity<Mouse> updateMouse(final @PathVariable("id") Integer mouseId,
                                           final @RequestBody Mouse mouse) {
    mouse.setId(mouseId);
    Mouse oldMouse = mouses.replace(mouseId, mouse);
    ResponseEntity<Mouse> status = oldMouse == null
        ? new ResponseEntity<Mouse>(HttpStatus.NOT_FOUND)
        : new ResponseEntity<Mouse>(oldMouse, HttpStatus.OK);
    return status;
  }

}
