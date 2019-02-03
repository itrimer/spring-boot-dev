package com.demo.manager.impl;

import com.demo.common.BizException;
import com.demo.common.RedisLockUtil;
import com.demo.dao.InventoryDao;
import com.demo.manager.InventoryManager;
import com.demo.model.Inventory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InventoryManagerImpl implements InventoryManager {

    @Resource
    private InventoryDao inventoryDao;

    @Override
    public synchronized String addInventory(Long productId, Long qty) throws Exception {
        Inventory inventory = inventoryDao.getByProductId(productId);
        if (inventory == null) {
            inventory = new Inventory();
            inventory.setProductId(productId);
            inventory.setQty(qty);
            inventory.setLockQty(0L);
        } else {
            Long qty1 = inventory.getQty();
            qty1 += qty;
            inventory.setQty(qty1);
        }

        inventoryDao.save(inventory);

        return "OK";
    }

    @Override
    public synchronized String lockInventory(Long productId, Long qty) throws Exception {
        Inventory inventory = inventoryDao.getByProductId(productId);
        if (inventory == null) {
            throw new BizException(400, "找不到库存");
        }

        Long lockQty = inventory.getLockQty();
        if (lockQty == null) {
            lockQty = 0L;
        }

        lockQty += qty;
        if (lockQty > inventory.getQty()) {
            throw new BizException(510, "库存不足，have=" + inventory.getQty()
                    + ",locked=" + inventory.getLockQty() + ",require=" + qty + ",lack=" + (lockQty - inventory.getQty()));
        }

        inventory.setLockQty(lockQty);
        inventoryDao.save(inventory);

        return "OK";
    }

    @Override
    public String addInventoryWithRedis(Long productId, Long qty) throws Exception {
        RedisLockUtil redisLockUtil = new RedisLockUtil("inventory");
        redisLockUtil.lock();
        try {
            Inventory inventory = inventoryDao.getByProductId(productId);
            if (inventory == null) {
                inventory = new Inventory();
                inventory.setProductId(productId);
                inventory.setQty(qty);
                inventory.setLockQty(0L);
            } else {
                Long qty1 = inventory.getQty();
                qty1 += qty;
                inventory.setQty(qty1);
            }
            inventoryDao.save(inventory);
        } finally {
            redisLockUtil.unlock();
        }
        return "OK";
    }

    @Override
    public String lockInventoryWithRedis(Long productId, Long qty) throws Exception {
        RedisLockUtil redisLockUtil = new RedisLockUtil("inventory");
        redisLockUtil.lock();
        try {
            Inventory inventory = inventoryDao.getByProductId(productId);
            if (inventory == null) {
                throw new BizException(400, "找不到库存");
            }

            Long lockQty = inventory.getLockQty();
            if (lockQty == null) {
                lockQty = 0L;
            }

            lockQty += qty;
            if (lockQty > inventory.getQty()) {
                throw new BizException(510, "库存不足，have=" + inventory.getQty()
                        + ",locked=" + inventory.getLockQty() + ",require=" + qty + ",lack=" + (lockQty - inventory.getQty()));
            }

            inventory.setLockQty(lockQty);
            inventoryDao.save(inventory);
        } finally {
            redisLockUtil.unlock();
        }
        return "OK";
    }

    @Override
    public String addInventoryWithVersion(Long productId, Long qty) throws Exception {
        Inventory inventory = inventoryDao.getByProductId(productId);
        if (inventory == null) {
            inventory = new Inventory();
            inventory.setProductId(productId);
            inventory.setQty(qty);
            inventory.setLockQty(0L);
            inventory.setVersion(0L);
            inventoryDao.save(inventory);
        } else {
            Long qty1 = inventory.getQty();
            qty1 += qty;
            int eftCnt = inventoryDao.update(inventory.getInventoryId(), qty1, inventory.getLockQty(), inventory.getVersion());
            if(eftCnt == 0){
                throw new RuntimeException("版本号不正确:" + inventory.getVersion());
            }
        }

        return "OK";
    }

    @Override
    public String lockInventoryWithVersion(Long productId, Long qty) throws Exception {
        Inventory inventory = inventoryDao.getByProductId(productId);
        if (inventory == null) {
            throw new BizException(400, "找不到库存");
        }

        Long lockQty = inventory.getLockQty();
        if (lockQty == null) {
            lockQty = 0L;
        }

        lockQty += qty;
        if (lockQty > inventory.getQty()) {
            throw new BizException(510, "库存不足，have=" + inventory.getQty()
                    + ",locked=" + inventory.getLockQty() + ",require=" + qty + ",lack=" + (lockQty - inventory.getQty()));
        }

        inventory.setLockQty(lockQty);
        inventoryDao.save(inventory);

        return "OK";
    }
}
