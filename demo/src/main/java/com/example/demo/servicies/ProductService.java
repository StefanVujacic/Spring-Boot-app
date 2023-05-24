package com.example.demo.servicies;

import com.example.demo.Dao.Cart_Dao;
import com.example.demo.Dao.Product_Dao;
import com.example.demo.Dao.User_Dao;
import com.example.demo.configuration.JWT_request_filter;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import com.example.demo.entity.User1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductService {

    private Product_Dao product_dao;
    private User_Dao user_dao;

    private Cart_Dao cart_dao;


    public ProductService(Product_Dao product_dao, User_Dao user_dao, Cart_Dao cart_dao) {
        this.product_dao = product_dao;
        this.user_dao = user_dao;
        this.cart_dao = cart_dao;
    }

    public Product addNewProduct (Product product) {
    return product_dao.save(product);

}
  public List<Product>findAll(){
    return product_dao.findAll();
  }
  public void delete_product_by_id(long id) {
    product_dao.deleteById(id);
  }

  public Product product_by_id(long product_id) {
   return product_dao.findById(product_id).get();
  }

  public List<Product> get_product_details (boolean isSingleProduct, long productId) {
   if (isSingleProduct) {
     List<Product>list=new ArrayList<>();
    Product product = product_dao.findById(productId).get();
     list.add(product);
     return list;
   }
   else {
      long id= Long.parseLong(JWT_request_filter.current_user);
      Optional<User1> user1 = Optional.ofNullable(user_dao.findById(id));
      List<Cart>carts=cart_dao.findByUser1(user1.get());

       return carts.stream().map(x->x.getProduct()).collect(Collectors.toList());
   }

  }

    public List<Product> getProductByPage(int pageNumber, String search_key) {
        PageRequest pageable = PageRequest.of(pageNumber, 14);
        Page<Product> page;
        if (search_key.equals("")) {
            page = product_dao.findAll(pageable);
        } else {
            page = product_dao.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(search_key,search_key,pageable);
        }
        return page.getContent();
    }


}
