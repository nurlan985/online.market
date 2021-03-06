package edu.miu.cs545.group01.online.market.service.impl;

import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.Seller;
import edu.miu.cs545.group01.online.market.domain.enums.Role;
import edu.miu.cs545.group01.online.market.domain.enums.UserStatus;
import edu.miu.cs545.group01.online.market.repository.SellerRepository;
import edu.miu.cs545.group01.online.market.service.FollowService;
import edu.miu.cs545.group01.online.market.service.SellerService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private FollowService followService;
    @Override
    public List<Seller> getPendingSellers() {
        return sellerRepository.findAllByStatusPending();
    }

    @Override
    public void approveSeller(long sellerId) throws NotFoundException{
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(()->new NotFoundException("Seller does not exist"));
        seller.setStatus(UserStatus.ACTIVE);
        seller.addRole(Role.SELLER);
        seller = sellerRepository.save(seller);
    }
    @Override
    public void rejectSeller(long sellerId) throws NotFoundException{
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(()->new NotFoundException("Seller does not exist"));
        seller.setStatus(UserStatus.REJECTED);
        seller = sellerRepository.save(seller);
    }

    @Override
    public Seller getSellerById(long sellerId) throws NotFoundException {
        return sellerRepository.findById(sellerId).orElseThrow(()->new NotFoundException("Seller is not found"));
    }

}
