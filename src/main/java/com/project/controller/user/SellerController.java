package com.project.controller.user;

import com.project.payload.request.user.SellerRequest;
import com.project.payload.request.user.SellerRequestWithoutPassword;
import com.project.payload.response.business.ResponseMessage;
import com.project.payload.response.user.CustomerResponse;
import com.project.payload.response.user.SellerResponse;
import com.project.service.user.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seller")
public class SellerController {
    private final SellerService sellerService;


    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ResponseMessage<SellerResponse>> saveSeller(@RequestBody @Valid SellerRequest sellerRequest) {
        return new ResponseEntity<>(sellerService.saveSeller(sellerRequest), HttpStatus.OK);
    }

    @PutMapping("/update/{sellerId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER')")
    public ResponseMessage<SellerResponse> updateSellerForAdmins(@PathVariable Long sellerId,
                                                                 @RequestBody @Valid SellerRequest sellerRequest) {

        return sellerService.updateSellerForAdmins(sellerId, sellerRequest);
    }

    @GetMapping("/getAllSellers")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Page<SellerResponse> getAllSellers(@RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "size", defaultValue = "20") int size,
                                              @RequestParam(value = "sort", defaultValue = "name") String sort,
                                              @RequestParam(value = "type", defaultValue = "desc") String type) {
        return sellerService.getAllSellers(page, size, sort, type);
    }

    @GetMapping("/getSeller/{sellerId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER')")
    public ResponseEntity<SellerResponse> getSellerById(@PathVariable Long sellerId) {

        return sellerService.getSellerById(sellerId);
    }

    @DeleteMapping("/deleteSeller/{sellerId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER')")
    public ResponseMessage deleteSellerById(@PathVariable Long sellerId) {

        return sellerService.deleteSellerById(sellerId);
    }

    @GetMapping("/getCustomers/{companyName}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER')")
    public List<CustomerResponse> getCustomersByCompanyName(@PathVariable String companyName) {
        return sellerService.getCustomersByCompanyName(companyName);
    }

    @PatchMapping("/updateOwnInfo")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public ResponseMessage<SellerResponse> updateOwnInfo(@RequestBody SellerRequestWithoutPassword sellerRequestWithoutPassword,
                                                         HttpServletRequest httpServletRequest){
        return sellerService.updateOwnInfo(sellerRequestWithoutPassword,httpServletRequest);
    }


}