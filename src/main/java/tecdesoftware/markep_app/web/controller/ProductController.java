package tecdesoftware.markep_app.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tecdesoftware.markep_app.domain.Product;
import tecdesoftware.markep_app.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
@Tag( name = "Product Controller", description = "Manage products in the store")
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET /products
    @GetMapping
    @Operation(
            summary = "Get all products",
            description = "Return a list of all available products"
    )
    @ApiResponse(responseCode = "200", description = "Succesful")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    // GET /products/all
    @GetMapping("/all")
    @Operation(
            summary = "Get all products ",
            description = "Return if a ID exist"
    )
    @ApiResponse(responseCode = "200", description = "Product found")
    @ApiResponse(responseCode = "404", description = "Product found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    // GET /products/{id}
    @GetMapping("/{id}")
    @Operation(
            summary = "Get all products by ID",
            description = "Return if a ID exist"
    )
    @ApiResponse(responseCode = "200", description = "Product found")
    @ApiResponse(responseCode = "404", description = "Product found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Product> getProduct(
            @Parameter(description = "ID of the product to be retrieved",
                    example = "7" ,
                    required = true
            )
            @PathVariable("id") int productId) {
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // GET /products/category/{categoryId}
    @GetMapping("/category/{categoryId}")
    @Operation(
            summary = "Get products by Category",
            description = "Return all products in a specific category"
    )
    @ApiResponse(responseCode = "200", description = "Product found in the category")
    @ApiResponse(responseCode = "404", description = "No product found in the category")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Product>> getByCategory(
            // TODO: ADD PARAMETER
            @Parameter(description = "category of the product to be retrieved",
                    example = "7" ,
                    required = true
            )
            @PathVariable("categoryId") int categoryId) {
        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST /products
    @PostMapping
    @Operation(
            summary = "save a new product",
            description = "Registers a new product and returns the created product ",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    value = """
                                {
                                   "name": "Butter beer",
                                   "categoryId": 2,
                                   "price": "19.58",
                                   "stock": 230,
                                   "active": true
                                }
                                """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "200", description = "Product succesfully created")
    @ApiResponse(responseCode = "400", description = "Invalid product data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "product conflict (duplicate ID)")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    // DELETE /products/{id}
    @DeleteMapping("/{id}")
    //TODO ADD ANOTATION
    @Operation(
            summary = "Delete products by Category",
            description = "Delete a specific  if exist"
    )
    @ApiResponse(responseCode = "200", description = "Product delete by id")
    @ApiResponse(responseCode = "400", description = "Invalid id product")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "No can't be deleted by id")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Boolean> delete(
            @Parameter(description = "ID of the product to be deleted",
                    example = "7" ,
                    required = true
            )
            @PathVariable("id") int productId) {
        if (productService.delete(productId)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}