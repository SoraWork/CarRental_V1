package com.hoaiphong.carrental.controllers;

import com.hoaiphong.carrental.controllers.utils.ImageUploadUtil;
import com.hoaiphong.carrental.dtos.car.CarCreateDTO;
import com.hoaiphong.carrental.dtos.car.CarUpdateDetailDTO;
import com.hoaiphong.carrental.dtos.car.CarUpdatePricingDTO;
import com.hoaiphong.carrental.dtos.car.CarUpdateStatusDTO;
import com.hoaiphong.carrental.dtos.messages.Message;
import com.hoaiphong.carrental.services.CarService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequestMapping("/car")
public class CarController {
    private final CarService carService;
    private ImageUploadUtil imageUploadUtil;

    public CarController(CarService carService, ImageUploadUtil imageUploadUtil) {
        this.imageUploadUtil = imageUploadUtil;
        this.carService = carService;
    }

    @RequestMapping
    public String index() {
        return "car/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        var carCreateDTO = new CarCreateDTO();
        model.addAttribute("carCreateDTO", carCreateDTO);
        return "car/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute CarCreateDTO carCreateDTO,
                         BindingResult bindingResult,
                         @RequestParam("registrationPaper") MultipartFile registrationPaper,
                         @RequestParam("certificateOfInspection") MultipartFile certificateOfInspection,
                         @RequestParam("insurance") MultipartFile insurance,
                         @RequestParam("imageFront") MultipartFile imageFront,
                         @RequestParam("imageBack") MultipartFile imageBack,
                         @RequestParam("imageLeft") MultipartFile imageLeft,
                         @RequestParam("imageRight") MultipartFile imageRight,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("carCreateDTO", carCreateDTO);
            return "car/create";
        }

        if (registrationPaper != null && !registrationPaper.isEmpty()) {
            try {
                byte[] bytes = registrationPaper.getBytes();
                // Create folder if not exist following format:
                // src/main/resources/static/documents/year/month/day
                LocalDateTime date = LocalDateTime.now();
                Path folder = Paths.get("src/main/resources/static/documents/" + date.getYear() + "/"
                        + date.getMonthValue() + "/" + date.getDayOfMonth());
                if (!Files.exists(folder)) {
                    Files.createDirectories(folder);
                }
                // Create file name following format: originalFileName + epochTime + extension
                String originalFileName = registrationPaper.getOriginalFilename();
                // Convert date to string epoch time
                Long epochTime = Instant.now().getEpochSecond();
                String fileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "-" + epochTime
                        + originalFileName.substring(originalFileName.lastIndexOf("."));
                Path path = Paths.get(folder.toString(), fileName);
                Files.write(path, bytes);
                carCreateDTO.setRegistrationPaper(folder.toString().replace("src\\main\\resources\\static", "") + "/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
                Message errorMessage = new Message("error", "Failed to upload file");
                model.addAttribute("message", errorMessage);
                var cars = carService.findAll();
                model.addAttribute("cars", cars);

                bindingResult.rejectValue("image", "image", "Failed to upload image");
                return "car/create";
            }
        }

        if (certificateOfInspection != null && !certificateOfInspection.isEmpty()) {
            try {
                byte[] bytes = certificateOfInspection.getBytes();
                // Create folder if not exist following format:
                // src/main/resources/static/documents/year/month/day
                LocalDateTime date = LocalDateTime.now();
                Path folder = Paths.get("src/main/resources/static/documents/" + date.getYear() + "/"
                        + date.getMonthValue() + "/" + date.getDayOfMonth());
                if (!Files.exists(folder)) {
                    Files.createDirectories(folder);
                }
                // Create file name following format: originalFileName + epochTime + extension
                String originalFileName = certificateOfInspection.getOriginalFilename();
                // Convert date to string epoch time
                Long epochTime = Instant.now().getEpochSecond();
                String fileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "-" + epochTime
                        + originalFileName.substring(originalFileName.lastIndexOf("."));
                Path path = Paths.get(folder.toString(), fileName);
                Files.write(path, bytes);
                carCreateDTO.setCertificateOfInspection(folder.toString().replace("src\\main\\resources\\static", "") + "/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
                Message errorMessage = new Message("error", "Failed to upload file");
                model.addAttribute("message", errorMessage);
                var cars = carService.findAll();
                model.addAttribute("cars", cars);

                bindingResult.rejectValue("image", "image", "Failed to upload image");
                return "car/create";
            }
        }

        if (insurance != null && !insurance.isEmpty()) {
            try {
                byte[] bytes = insurance.getBytes();
                // Create folder if not exist following format:
                // src/main/resources/static/documents/year/month/day
                LocalDateTime date = LocalDateTime.now();
                Path folder = Paths.get("src/main/resources/static/documents/" + date.getYear() + "/"
                        + date.getMonthValue() + "/" + date.getDayOfMonth());
                if (!Files.exists(folder)) {
                    Files.createDirectories(folder);
                }
                // Create file name following format: originalFileName + epochTime + extension
                String originalFileName = insurance.getOriginalFilename();
                // Convert date to string epoch time
                Long epochTime = Instant.now().getEpochSecond();
                String fileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "-" + epochTime
                        + originalFileName.substring(originalFileName.lastIndexOf("."));
                Path path = Paths.get(folder.toString(), fileName);
                Files.write(path, bytes);
                carCreateDTO.setRegistrationPaper(folder.toString().replace("src\\main\\resources\\static", "") + "/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
                Message errorMessage = new Message("error", "Failed to upload file");
                model.addAttribute("message", errorMessage);
                var cars = carService.findAll();
                model.addAttribute("cars", cars);

                bindingResult.rejectValue("image", "image", "Failed to upload image");
                return "car/create";
            }
        }

        if (imageFront != null && !imageFront.isEmpty()) {
            try {
                byte[] bytes = imageFront.getBytes();
                // Create folder if not exist following format:
                // src/main/resources/static/images/recipes/year/month/day
                LocalDateTime date = LocalDateTime.now();
                Path folder = Paths.get("src/main/resources/static/images/cars/" + date.getYear() + "/"
                        + date.getMonthValue() + "/" + date.getDayOfMonth());
                if (!Files.exists(folder)) {
                    Files.createDirectories(folder);
                }
                // Create file name following format: originalFileName + epochTime + extension
                String originalFileName = imageFront.getOriginalFilename();
                // Convert date to string epoch time
                Long epochTime = Instant.now().getEpochSecond();
                String fileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "-" + epochTime
                        + originalFileName.substring(originalFileName.lastIndexOf("."));
                Path path = Paths.get(folder.toString(), fileName);
                Files.write(path, bytes);
                carCreateDTO.setImageFront(folder.toString().replace("src\\main\\resources\\static", "") + "/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
                Message errorMessage = new Message("error", "Failed to upload image");
                model.addAttribute("message", errorMessage);
                var cars = carService.findAll();
                model.addAttribute("cars", cars);

                bindingResult.rejectValue("image", "image", "Failed to upload image");
                return "car/create";
            }
        }

        if (imageBack != null && !imageBack.isEmpty()) {
            try {
                byte[] bytes = imageBack.getBytes();
                // Create folder if not exist following format:
                // src/main/resources/static/images/recipes/year/month/day
                LocalDateTime date = LocalDateTime.now();
                Path folder = Paths.get("src/main/resources/static/images/cars/" + date.getYear() + "/"
                        + date.getMonthValue() + "/" + date.getDayOfMonth());
                if (!Files.exists(folder)) {
                    Files.createDirectories(folder);
                }
                // Create file name following format: originalFileName + epochTime + extension
                String originalFileName = imageBack.getOriginalFilename();
                // Convert date to string epoch time
                Long epochTime = Instant.now().getEpochSecond();
                String fileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "-" + epochTime
                        + originalFileName.substring(originalFileName.lastIndexOf("."));
                Path path = Paths.get(folder.toString(), fileName);
                Files.write(path, bytes);
                carCreateDTO.setImageBack(folder.toString().replace("src\\main\\resources\\static", "") + "/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
                Message errorMessage = new Message("error", "Failed to upload image");
                model.addAttribute("message", errorMessage);
                var cars = carService.findAll();
                model.addAttribute("cars", cars);

                bindingResult.rejectValue("image", "image", "Failed to upload image");
                return "car/create";
            }
        }

        if (imageLeft != null && !imageLeft.isEmpty()) {
            try {
                byte[] bytes = imageLeft.getBytes();
                // Create folder if not exist following format:
                // src/main/resources/static/images/recipes/year/month/day
                LocalDateTime date = LocalDateTime.now();
                Path folder = Paths.get("src/main/resources/static/images/cars/" + date.getYear() + "/"
                        + date.getMonthValue() + "/" + date.getDayOfMonth());
                if (!Files.exists(folder)) {
                    Files.createDirectories(folder);
                }
                // Create file name following format: originalFileName + epochTime + extension
                String originalFileName = imageLeft.getOriginalFilename();
                // Convert date to string epoch time
                Long epochTime = Instant.now().getEpochSecond();
                String fileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "-" + epochTime
                        + originalFileName.substring(originalFileName.lastIndexOf("."));
                Path path = Paths.get(folder.toString(), fileName);
                Files.write(path, bytes);
                carCreateDTO.setImageLeft(folder.toString().replace("src\\main\\resources\\static", "") + "/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
                Message errorMessage = new Message("error", "Failed to upload image");
                model.addAttribute("message", errorMessage);
                var cars = carService.findAll();
                model.addAttribute("cars", cars);

                bindingResult.rejectValue("image", "image", "Failed to upload image");
                return "car/create";
            }
        }

        if (imageRight != null && !imageRight.isEmpty()) {
            try {
                byte[] bytes = imageRight.getBytes();
                // Create folder if not exist following format:
                // src/main/resources/static/images/recipes/year/month/day
                LocalDateTime date = LocalDateTime.now();
                Path folder = Paths.get("src/main/resources/static/images/cars/" + date.getYear() + "/"
                        + date.getMonthValue() + "/" + date.getDayOfMonth());
                if (!Files.exists(folder)) {
                    Files.createDirectories(folder);
                }
                // Create file name following format: originalFileName + epochTime + extension
                String originalFileName = imageRight.getOriginalFilename();
                // Convert date to string epoch time
                Long epochTime = Instant.now().getEpochSecond();
                String fileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "-" + epochTime
                        + originalFileName.substring(originalFileName.lastIndexOf("."));
                Path path = Paths.get(folder.toString(), fileName);
                Files.write(path, bytes);
                carCreateDTO.setImageRight(folder.toString().replace("src\\main\\resources\\static", "") + "/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
                Message errorMessage = new Message("error", "Failed to upload image");
                model.addAttribute("message", errorMessage);
                var cars = carService.findAll();
                model.addAttribute("cars", cars);

                bindingResult.rejectValue("image", "image", "Failed to upload image");
                return "car/create";
            }
        }


        var result = carService.create(carCreateDTO);
        if (result == null) {
            var errorMessage = new Message("error", "Failed to create car");
            model.addAttribute("message", errorMessage);
            return "car/create";
        }
        var successMessage = new Message("success", "Category created successfully");
        redirectAttributes.addFlashAttribute("message", successMessage);
        return "redirect:car/mycars";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable UUID id, Model model) {
        var carDTO = carService.findById(id);
        var carUpdateDetailDTO = carService.findById(id);
        var carUpdatePricingDTO = carService.findById(id);
        var carUpdateStatusDTO = carService.findById(id);

        model.addAttribute("carUpdateStatusDTO", carUpdateStatusDTO);
        model.addAttribute("carUpdatePricingDTO", carUpdatePricingDTO);
        model.addAttribute("carUpdateDetailDTO", carUpdateDetailDTO);
        model.addAttribute("carDTO", carDTO);
        return "car/edit-car";
    }

    @PostMapping("/edit-detail/{id}")
    public String edit(@PathVariable UUID id,
                       @ModelAttribute @Valid CarUpdateDetailDTO carUpdateDetailDTO,
                       RedirectAttributes redirectAttributes,
                       @RequestParam("imageFrontFile") MultipartFile imageFrontFile,
                       @RequestParam("imageBackFile") MultipartFile imageBackFile,
                       @RequestParam("imageLeftFile") MultipartFile imageLeftFile,
                       @RequestParam("imageRightFile") MultipartFile imageRightFile,
                       BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("carUpdateDetailDTO", carUpdateDetailDTO);
            return "car/edit";
        }

        var oldcar = carService.findById(id);
        // Upload từng hình ảnh
        String imageFrontPath = imageUploadUtil.uploadImage(imageFrontFile, oldcar.getImageFront(), model, bindingResult);
        if (imageFrontPath == null) {
            return "car/create"; // Upload thất bại
        } else {
            carUpdateDetailDTO.setImageFront(imageFrontPath);
        }

        String imageBackPath = imageUploadUtil.uploadImage(imageBackFile, oldcar.getImageBack(), model, bindingResult);
        if (imageBackPath == null) {
            return "car/create"; // Upload thất bại
        } else {
            carUpdateDetailDTO.setImageBack(imageBackPath);
        }

        String imageLeftPath = imageUploadUtil.uploadImage(imageLeftFile, oldcar.getImageLeft(), model, bindingResult);
        if (imageLeftPath == null) {
            return "car/create"; // Upload thất bại
        } else {
            carUpdateDetailDTO.setImageLeft(imageLeftPath);
        }

        String imageRightPath = imageUploadUtil.uploadImage(imageRightFile, oldcar.getImageRight(), model, bindingResult);
        if (imageRightPath == null) {
            return "car/create"; // Upload thất bại
        } else {
            carUpdateDetailDTO.setImageRight(imageRightPath);
        }

        var result = carService.update(id, carUpdateDetailDTO);
        if (result == null) {
            var errorMessage = new Message("error", "Failed to update car");
            model.addAttribute("message", errorMessage);
            return "car/edit-car";
        }
        var successMessage = new Message("success", "Car updated successfully");
        redirectAttributes.addFlashAttribute("message", successMessage);
        return "owner/owner";
    }

    @PostMapping("/edit-pricing/{id}")
    public String edit(@PathVariable UUID id,
                       @ModelAttribute @Valid CarUpdatePricingDTO carUpdatePricingDTO,
                       RedirectAttributes redirectAttributes,
                       BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("carUpdatePricingDTO", carUpdatePricingDTO);
            return "car/edit-car";
        }

        var result = carService.update(id, carUpdatePricingDTO);
        if (result == null) {
            var errorMessage = new Message("error", "Failed to update car");
            model.addAttribute("message", errorMessage);
            return "car/edit-car";
        }
        var successMessage = new Message("success", "Car updated successfully");
        redirectAttributes.addFlashAttribute("message", successMessage);
        return "owner/owner";
    }

    @PostMapping("/edit-status/{id}")
    public String edit(@PathVariable UUID id,
                       @ModelAttribute @Valid CarUpdateStatusDTO carUpdateStatusDTO,
                       RedirectAttributes redirectAttributes,
                       BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("carUpdateStatusDTO", carUpdateStatusDTO);
            return "car/edit-car";
        }
        var result = carService.update(id, carUpdateStatusDTO);
        if (result == null) {
            var errorMessage = new Message("error", "Failed to update car");
            model.addAttribute("message", errorMessage);
            return "car/edit-car";
        }
        var successMessage = new Message("success", "Car updated successfully");
        redirectAttributes.addFlashAttribute("message", successMessage);
        return "owner/owner";
    }

    @GetMapping("/list")
    public String list(Model model) {
        var cars = carService.findAll();
        model.addAttribute("cars", cars);
        return "car/list";
    }

    @GetMapping("editstatus/{id}")
    public String editstatus(@PathVariable UUID id, Model model) {
        var car = carService.findById(id);
        model.addAttribute("car", car);
        return "car/detail";
    }

    @PostMapping("editstatus/{id}")
    public String editstatus(@PathVariable UUID id,
    RedirectAttributes redirectAttributes,
    BindingResult bindingResult, Model model) {
        var car = carService.findById(id);
        model.addAttribute("car", car);
        return "car/detail";
    }

}

