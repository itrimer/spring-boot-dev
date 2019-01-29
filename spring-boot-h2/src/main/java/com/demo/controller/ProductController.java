package com.demo.controller;

import com.demo.common.BizException;
import com.demo.dao.InventoryDao;
import com.demo.dao.ProductDao;
import com.demo.manager.InventoryManager;
import com.demo.model.Article;
import com.demo.model.Inventory;
import com.demo.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhigang.huang on 2017/12/4.
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductDao productDao;
    @Autowired
    private InventoryDao inventoryDao;
    @Autowired
    private InventoryManager inventoryManager;

    @ResponseBody
    @RequestMapping("/add")
    public Map<String, Object> addProduct(Product product, Model model) {
        boolean hasError = false;
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Assert.notNull(product, "参数不能为空");
            Assert.notNull(product.getProductCode(), "productCode不能为空");
            Assert.notNull(product.getProductTitle(), "productTitle不能为空");
        } catch (Exception e) {
            logger.error("发生异常", e);
            hasError = true;
            resultMap.put("code", "500");
            resultMap.put("error_msg", e.getMessage());
        }

        if (hasError) {
            return resultMap;
        }

        Product newProduct = productDao.save(product);

        resultMap.put("code", "0");
        resultMap.put("error_msg", "OK");
        resultMap.put("data", newProduct);

        return resultMap;
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<Product> list(Model model) {
        Iterable<Product> iterator = productDao.findAll();

        List<Product> list = new ArrayList<>();
        iterator.forEach(single -> {
            list.add(single);
        });
        return list;
    }

    @ResponseBody
    @RequestMapping("/listInventory")
    public List<Inventory> listInventory(Model model) {
        Iterable<Inventory> iterator = inventoryDao.findAll();

        List<Inventory> list = new ArrayList<>();
        iterator.forEach(single -> {
            list.add(single);
        });
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/addInventory")
    public Map<String, Object> addInventory(Inventory inventory, Model model) {
        boolean hasError = false;
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Assert.notNull(inventory, "参数不能为空");
            Assert.notNull(inventory.getProductId(), "productId不能为空");
            Assert.notNull(inventory.getQty(), "qty不能为空");
        } catch (Exception e) {
            logger.error("发生异常", e);
            hasError = true;
            resultMap.put("code", "500");
            resultMap.put("error_msg", e.getMessage());
        }

        if (hasError) {
            return resultMap;
        }
        try {
            String doResut = inventoryManager.addInventory(inventory.getProductId(), inventory.getQty());
            if ("OK".equals(doResut)) {
                resultMap.put("code", "0");
                resultMap.put("error_msg", "OK");
            } else {
                resultMap.put("code", "300");
                resultMap.put("error_msg", "占用库存异常：" + doResut);
            }
        } catch (BizException e) {
            int code = e.getCode();
            String errorMsg = e.getErrorMsg();
            resultMap.put("code", String.valueOf(code));
            resultMap.put("error_msg", errorMsg);
        } catch (Exception e) {
            logger.error("发生异常", e);
            resultMap.put("code", "510");
            resultMap.put("error_msg", "发生异常：" + e.getMessage());
        }
        return resultMap;
    }

    @ResponseBody
    @RequestMapping("/sell")
    public Map<String, Object> sell(Long productId, Long qty, Model model) {
        boolean hasError = false;
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Assert.notNull(productId, "productId不能为空");
            Assert.notNull(qty, "qty不能为空");
        } catch (Exception e) {
            logger.error("发生异常", e);
            hasError = true;
            resultMap.put("code", "500");
            resultMap.put("error_msg", e.getMessage());
        }
        if (hasError) {
            return resultMap;
        }
        try {
            String doResut = inventoryManager.lockInventory(productId, qty);
            if ("OK".equals(doResut)) {
                resultMap.put("code", "0");
                resultMap.put("error_msg", "OK");
            } else {
                resultMap.put("code", "300");
                resultMap.put("error_msg", "占用库存异常：" + doResut);
            }
        } catch (BizException e) {
            int code = e.getCode();
            String errorMsg = e.getErrorMsg();
            resultMap.put("code", String.valueOf(code));
            resultMap.put("error_msg", errorMsg);
        } catch (Exception e) {
            logger.error("发生异常", e);
            resultMap.put("code", "510");
            resultMap.put("error_msg", "发生异常：" + e.getMessage());
        }
        return resultMap;
    }
}
