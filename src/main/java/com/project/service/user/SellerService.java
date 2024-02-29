package com.project.service.user;

import com.project.domain.concrets.user.User;
import com.project.domain.enums.RoleType;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.mappers.UserMapper;
import com.project.payload.messages.ExceptionMessages;
import com.project.payload.messages.SuccessMessages;
import com.project.payload.request.user.SellerRequest;
import com.project.payload.request.user.SellerRequestWithoutPassword;
import com.project.payload.response.business.ResponseMessage;
import com.project.payload.response.user.CustomerResponse;
import com.project.payload.response.user.SellerResponse;
import com.project.repository.user.UserRepository;
import com.project.service.helper.MethodHelper;
import com.project.service.helper.PageableHelper;
import com.project.service.validation.UniqueValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final UserRepository userRepository;
    private final UniqueValidator uniqueValidator;
    private final UserMapper userMapper;
    private final UserRoleService userRoleService;
    private final MethodHelper methodHelper;
    private final PageableHelper pageableHelper;

    public ResponseMessage<SellerResponse> saveSeller(SellerRequest sellerRequest) {

        uniqueValidator.checkUniqueCondition(sellerRequest);

        User seller = userMapper.mapSellerRequestToUser(sellerRequest);

        seller.setUserRole(userRoleService.getUserRole(RoleType.SELLER));

        //TODO sellerin sattigi urunleri bir listte saklamak icin gerekli islemleri hallet.


        return ResponseMessage.<SellerResponse>builder().object(userMapper.mapUserToSellerResponse(userRepository.save(seller)))
                .message(SuccessMessages.SELLER_SAVED_SUCCESSFULLY).httpStatus(HttpStatus.CREATED).build();


    }

    public ResponseMessage<SellerResponse> updateSellerForAdmins(Long sellerId, SellerRequest sellerRequest) {

        User seller = methodHelper.findUserOrThrowException(sellerId);

        methodHelper.checkRole(seller, RoleType.SELLER);

        uniqueValidator.checkUniqueCondition(sellerRequest);

        User sellerForUpdate = userMapper.mapSellerRequestToUserForUpdate(seller, sellerRequest);

        userRepository.save(sellerForUpdate);

        return ResponseMessage.<SellerResponse>builder().message(SuccessMessages.SELLER_UPDATED_SUCCESSFULLY)
                .object(userMapper.mapUserToSellerResponse(userRepository.save(sellerForUpdate))).httpStatus(HttpStatus.OK).build();

    }

    public Page<SellerResponse> getAllSellers(int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.returnPageable(page, size, sort, type);

        return userRepository.findAll(pageable).map(userMapper::mapUserToSellerResponse);
    }

    public ResponseEntity<SellerResponse> getSellerById(Long sellerId) {

        User seller = methodHelper.findUserOrThrowException(sellerId);

        return ResponseEntity.ok(userMapper.mapUserToSellerResponse(seller));

    }

    public ResponseMessage deleteSellerById(Long sellerId) {

        if (!userRepository.existsById(sellerId)) {
            throw new ResourceNotFoundException(ExceptionMessages.SELLER_NOT_FOUND);
        }

        userRepository.deleteById(sellerId);

        return ResponseMessage.builder().httpStatus(HttpStatus.OK).message(SuccessMessages.SELLER_DELETED_SUCCESSFULLY).build();
    }

    public List<CustomerResponse> getCustomersByCompanyName(String companyName) {

        if (!userRepository.existsCompanyName(companyName)){
            throw new ResourceNotFoundException(String.format(ExceptionMessages.COMPANY_NOT_FOUND,companyName));
        }

        /*List<User> allCustomersByCompanyName = userRepository.findAllByCompanyName(companyName);

        return allCustomersByCompanyName.stream().map(userMapper::mapUserToCustomerResponse).collect(Collectors.toList());*/

        return userRepository.findAllByCompanyName(companyName).stream()
                .map(userMapper::mapUserToCustomerResponse).collect(Collectors.toList());
    }

    public ResponseMessage<SellerResponse> updateOwnInfo(SellerRequestWithoutPassword sellerRequestWithoutPassword, HttpServletRequest httpServletRequest) {

        uniqueValidator.checkUniqueCondition(sellerRequestWithoutPassword);

        String userName = httpServletRequest.getHeader("userName");
        User seller = userRepository.findByUserNameEquals(userName);

        User sellerForUpdate = userMapper.mapSellerRequestToUserForUpdateOwnInfo(seller,sellerRequestWithoutPassword);

        return ResponseMessage.<SellerResponse>builder().message(SuccessMessages.SELLER_UPDATED_SUCCESSFULLY).httpStatus(HttpStatus.OK)
                .object(userMapper.mapUserToSellerResponse(userRepository.save(sellerForUpdate))).build();

    }
}
