package tecdesoftware.markep_app.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tecdesoftware.markep_app.domain.Purchase;
import tecdesoftware.markep_app.domain.service.PurchaseService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchases")
@Tag(name = "Purchase Controller", description = "Manage purchases made by clients")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    // GET /purchases/all
    @GetMapping("/all")
    @Operation(
            summary = "Get all purchases",
            description = "Returns a list of all registered purchases"
    )
    @ApiResponse(responseCode = "200", description = "Successful")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    // GET /purchases/client/{idClient}
    @GetMapping("/client/{idClient}")
    @Operation(
            summary = "Get purchases by client ID",
            description = "Returns a list of purchases made by the given client ID"
    )
    @ApiResponse(responseCode = "200", description = "Purchases found for the client")
    @ApiResponse(responseCode = "404", description = "No purchases found for the client")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Purchase>> getByClient(
            @Parameter(description = "ID of the client whose purchases are to be retrieved",
                    example = "1", required = true)
            @PathVariable Integer idClient) {
        return purchaseService.getByClient(idClient)
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST /purchases/save
    @PostMapping("/save")
    @Operation(
            summary = "Save a new purchase",
            description = "Registers a new purchase made by a client and returns the created purchase",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    value = """
                                    {
                                      "clientId": 1,
                                      "date": "2024-07-21T00:00:00",
                                      "paymentMethod": "cash",
                                      "comment": "Thank you for your purchase!",
                                      "state": "COMPLETED",
                                      "products": [
                                        {
                                          "productId": 2,
                                          "quantity": 3,
                                          "total": 59.97
                                        }
                                      ]
                                    }
                                    """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Purchase successfully created")
    @ApiResponse(responseCode = "400", description = "Invalid purchase data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Purchase> save(
            @RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}
