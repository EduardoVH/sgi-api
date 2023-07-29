package com.sgi.assetsapi.repositories;

import com.sgi.assetsapi.entities.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAssetRepository extends JpaRepository<Asset, Long> {
}
