package com.example.demo.service;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    // Inyección por constructor
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {
        return productoRepository.findById(id)
                .map(existente -> {
                    existente.setName(producto.getName());
                    existente.setDescription(producto.getDescription());
                    existente.setPrice(producto.getPrice());
                    existente.setMarca(producto.getMarca());
                    existente.setModel(producto.getModel());
                    existente.setYear(producto.getYear());
                    existente.setRam(producto.getRam());
                    existente.setStorage(producto.getStorage());
                    existente.setProcessor(producto.getProcessor());
                    existente.setImageUrl(producto.getImageUrl());
                    return productoRepository.save(existente);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    @Override
    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
        productoRepository.deleteById(id);
    }
}

