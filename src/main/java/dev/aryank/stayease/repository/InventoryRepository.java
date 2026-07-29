package dev.aryank.stayease.repository;

import dev.aryank.stayease.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
