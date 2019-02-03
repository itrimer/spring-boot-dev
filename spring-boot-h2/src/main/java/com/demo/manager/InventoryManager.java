package com.demo.manager;

public interface InventoryManager {
    public String addInventory(Long productId, Long qty) throws Exception;

    public String lockInventory(Long productId, Long qty) throws Exception;

    public String addInventoryWithRedis(Long productId, Long qty) throws Exception;

    public String lockInventoryWithRedis(Long productId, Long qty) throws Exception;

    public String addInventoryWithVersion(Long productId, Long qty) throws Exception;

    public String lockInventoryWithVersion(Long productId, Long qty) throws Exception;
}
