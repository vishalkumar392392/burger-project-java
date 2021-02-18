package com.react.burger.project.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.react.burger.project.entity.Customer;
import com.react.burger.project.entity.Details;
import com.react.burger.project.modal.Address;
import com.react.burger.project.modal.Ingredient;
import com.react.burger.project.modal.Order;
import com.react.burger.project.modal.OrderRequest;
import com.react.burger.project.modal.ResultModal;
import com.react.burger.project.modal.Results;
import com.react.burger.project.modal.TestIngredient;
import com.react.burger.project.service.BurgerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BurgerController {

	@Autowired
	private BurgerService burgerService;

	private int id = 100;

	private List<TestIngredient> list = new ArrayList<>();

	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {

		return burgerService.getAllCustomers();
	}

	@PostMapping(path = "/order")
	public String placeOrder(@RequestBody OrderRequest orderRequest) {

		Order order = new Order();
		com.react.burger.project.modal.Customer customer = new com.react.burger.project.modal.Customer();
		order.setIngredients(orderRequest.getIngredients());
		order.setPrice(orderRequest.getPrice());
		customer.setEmail(orderRequest.getCustomer().getEmail());
		customer.setName(orderRequest.getCustomer().getName());
		customer.setPhoneNumber(null);
		Address address = new Address();
		address.setCountry(orderRequest.getCustomer().getCountry());
		address.setStreet(orderRequest.getCustomer().getStreet());
		address.setZipCode(orderRequest.getCustomer().getZipCode());
		customer.setAddress(address);
		order.setCustomer(customer);
		order.setDelivery(orderRequest.getCustomer().getDelivery());
		order.setItems(orderRequest.getItems());
		return burgerService.placeOrder(order);
	}

	@GetMapping(path = "/ingredients")
	public Ingredient getIngredients() {
		return burgerService.getIngredients();
	}

	@GetMapping(path = "/order")
	public List<com.react.burger.project.entity.Order> getAllOrders() {
		return burgerService.getAllOrders();
	}

	@PostMapping(path = "/ing")
	public Integer addIng(@RequestBody TestIngredient ing) {
		int newId = id++;
		ing.setId(newId);
		list.add(ing);
		return newId;
	}

	@GetMapping(path = "/ing")
	public List<TestIngredient> getIng() {
		return list;
	}

	@GetMapping(path = "/ings")
	public List<TestIngredient> getIngQuery(@RequestParam(required = false, name = "title") String title) {
		if (Objects.nonNull(title)) {
			return list.stream().filter(res -> res.getTitle().contains(title)).collect(Collectors.toList());
		}
		return list;
	}

	@GetMapping(path = "/ing/{id}")
	public String delete(@PathVariable(value = "id") int id) {
		return "Success";
	}

	@GetMapping("/images")
	public void getImages() throws URISyntaxException, JsonMappingException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		URI url = new URI("https://randomuser.me/api/?results=50");

		ResponseEntity<String> res = restTemplate.getForEntity(url, String.class);
		ResultModal results = mapper.readValue(res.getBody(), ResultModal.class);
		List<Results> list = results.getResults();
		List<String> pics = list.stream().map(x -> x.getPicture().getLarge()).collect(Collectors.toList());
	}

	@GetMapping("/excel")
	public ResponseEntity<InputStreamResource> getUserById() throws IOException {

		String[] sheet1columnNames = { "Customer Id", "Name", "Email", "Contact No.", "Address","Total Orders" };

		XSSFWorkbook wb = new XSSFWorkbook();

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		XSSFSheet sheet1 = wb.createSheet("Customers");

		CellStyle style;
		Font titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 12);
		titleFont.setBold(true);
		titleFont.setFontName("Arial");
		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.LEFT);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFont(titleFont);

		Row titleRow = sheet1.createRow(0);

		Cell titleCell = null;
		
		for (int i = 0; i <= 5; i++) {
			titleCell = titleRow.createCell(i);
			titleCell.setCellStyle(style);
			titleCell.setCellValue(sheet1columnNames[i]);
			sheet1.setColumnWidth(i, 26 * 256);

		}

		List<Details> details = burgerService.getDetails();

		AtomicInteger rowCount = new AtomicInteger(1);
		
		Row row = null;
		Cell cell = null;
		CellStyle cellstyle;
		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setBold(false);
		font.setFontName("Arial");
		cellstyle = wb.createCellStyle();
		cellstyle.setAlignment(HorizontalAlignment.LEFT);
		cellstyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cellstyle.setFont(font);
		for (Details detail : details) {
			row = sheet1.createRow(rowCount.getAndIncrement());
			cell = row.createCell(0);
			cell.setCellStyle(cellstyle);
			cell.setCellValue(detail.getId());
			row.createCell(1).setCellValue(detail.getCustomer());
			row.createCell(2).setCellValue(detail.getEmail());
			row.createCell(3).setCellValue(detail.getPhonenumber());
			row.createCell(4).setCellValue(detail.getCustomerAddress());
			cell = row.createCell(5);
			cell.setCellStyle(cellstyle);
			cell.setCellValue(detail.getOrders());

		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=Customers.xlsx");
		wb.write(out);
		ByteArrayInputStream a = new ByteArrayInputStream(out.toByteArray());

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(a));
	}
	
	@GetMapping("/address")
	public List<com.react.burger.project.entity.Address> getAllAddress(){
		
		return burgerService.getAllAddress();
	}

}
