package com.shikha.project.login.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shikha.project.login.model.FileEntity;
import com.shikha.project.login.service.AsymmetricCryptography;
import com.shikha.project.login.service.AsymmetricDecryptionService;
import com.shikha.project.login.service.EncryptionException;
import com.shikha.project.login.service.FileUploaderService;

@Controller
public class FileUploadController {

	@Autowired
	private FileUploaderService storageService;
	
	@Autowired
	private AsymmetricCryptography cryptoEncryption;
	
	@Autowired
	private AsymmetricDecryptionService cryptoDecryption;
	


	@RequestMapping(value = "/encryptFile", method = RequestMethod.POST)
    public ModelAndView encryptFile(@RequestParam("file") MultipartFile file) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
			
        ModelAndView modelAndView = new ModelAndView();
        
        modelAndView.addObject("fileName", file.getOriginalFilename());
        modelAndView.addObject("fileText", new String(file.getBytes()));
        modelAndView.addObject("fileEncryptedText", cryptoEncryption.encryptText(file.getBytes()));
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
	
	@RequestMapping(value = "/uploadEncryptFile", method = RequestMethod.POST)
    public ModelAndView handleFileUpload(@RequestParam String encryptedText, @RequestParam String fileName) throws IOException, EncryptionException {
		ModelAndView modelAndView = new ModelAndView();
	
		storageService.store(encryptedText.getBytes(), fileName);
        
        modelAndView.addObject("fileUploadSuccess", "File upload successfull");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
	
	@RequestMapping(value="getFiles", method=RequestMethod.GET)
	public ModelAndView getFilesPerUser() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("filesList", storageService.getFiles());
		 modelAndView.setViewName("admin/home");
		 return modelAndView;
	}
	
}
