package com.androidcargo.spring.controllers;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import javax.sql.rowset.serial.SerialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.androidcargo.spring.models.data.DriverData;
import com.androidcargo.spring.service.impl.DataService;

@RestController
@RequestMapping("/data")
public class ImageDataController {
  private final DataService dataService;

  @Autowired
  public ImageDataController(DataService dataService) {
    this.dataService = dataService;
  }

  @GetMapping("/displayDriverData")
  public ResponseEntity<byte[]> displayDriverData(@RequestParam("id") long id)
      throws IOException, SQLException {
    DriverData driverData = dataService.viewDriverDataById(id);
    byte[] imageBytes = null;
    imageBytes = driverData.getImage().getBytes(1, (int) driverData.getImage().length());
    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
  }

  @GetMapping("/addDriverData")
  public ModelAndView addDriverData() {
    return new ModelAndView("addimage");
  }

  @PostMapping("/addDriverData")
  public String addDriverData(HttpServletRequest request,
      @RequestParam("driverData") MultipartFile file)
      throws IOException, SerialException, SQLException {
    byte[] bytes = file.getBytes();
    Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

    DriverData driverData = new DriverData();
    driverData.setImage(blob);
    dataService.createDriverData(driverData);
    return "redirect:/";
  }
}
