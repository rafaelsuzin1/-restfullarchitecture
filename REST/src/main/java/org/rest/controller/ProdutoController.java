package org.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.rest.Useful.OperatingResult;
import org.rest.model.Produto;
import org.rest.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired // Injeção de dependencias
	private ProdutoRepository produtoRepository;

	@RequestMapping(value = "/add/{dsProduto}", method = RequestMethod.POST) // Mapeando o método add para o servico rest
	public Produto add(@PathVariable String dsProduto) {
		OperatingResult resultado = new OperatingResult();
		Produto produto = new Produto(dsProduto);
		try {
			produtoRepository.save(produto);

			// Lista de links de possíveis ações com esse Produto
			resultado.add(linkTo(methodOn(ProdutoController.class).getProdutos()).withRel("Consultar todos os Produtos"));
			resultado.add(linkTo(methodOn(ProdutoController.class).getProduto(produto.getIdProduto())).withRel("Consultar Produto"));
			resultado.add(linkTo(methodOn(ProdutoController.class).update(produto.getIdProduto(), produto.getDsProduto())).withRel("Alterar Produto"));
			resultado.add(linkTo(methodOn(ProdutoController.class).remove(produto.getIdProduto())).withRel("Remover Produto"));

		} catch (Exception e) {
			resultado.setMessage(e.getMessage());
			resultado.setStatus(OperatingResult.FAILURE);
			e.printStackTrace();
		}
		return produto;
	}

	@RequestMapping(path = "/remove/{id}", method = RequestMethod.DELETE)
	public Produto remove(@PathVariable Long id) {
		OperatingResult resultado = new OperatingResult();
		Produto produto = produtoRepository.findOne(id);

		try {
			if (produto != null) {
				produtoRepository.delete(produto);
			} else{
				throw new RuntimeException("Produto não encontrado."); 
			}
			resultado.add(linkTo(methodOn(ProdutoController.class).getProdutos()).withRel("Consultar todos os Produtos"));

		} catch (Exception e) {
			resultado.setMessage(e.getMessage());
			resultado.setStatus(OperatingResult.FAILURE);
			e.printStackTrace();
		}
		System.out.println("Produto código " + id + " removido com sucesso."); 
		return produto;
	}

	@RequestMapping(path = "/update/{id}/{nome}", method = RequestMethod.PUT)
	public Produto update(@PathVariable Long id, @PathVariable String dsProduto) {
		OperatingResult resultado = new OperatingResult();
		Produto produto = produtoRepository.findOne(id);
		try {
			if (produto != null) {
				produto.setDsProduto(dsProduto);
				produtoRepository.save(produto);
			}

			resultado.add(linkTo(methodOn(ProdutoController.class).getProdutos()).withRel("Consultar todos os Produtos"));
			resultado.add(linkTo(methodOn(ProdutoController.class).getProduto(produto.getIdProduto())).withRel("Consultar Produto"));
			resultado.add(linkTo(methodOn(ProdutoController.class).update(produto.getIdProduto(), produto.getDsProduto())).withRel("Alterar Produto"));
			resultado.add(linkTo(methodOn(ProdutoController.class).remove(produto.getIdProduto())).withRel("Remover Produto"));

		} catch (Exception e) {
			resultado.setMessage(e.getMessage());
			resultado.setStatus(OperatingResult.FAILURE);
			e.printStackTrace();
		}
		return produto;
	}

	@RequestMapping(path = "/getProduto/{idProduto}", method = RequestMethod.GET)
	public Produto getProduto(@PathVariable Long idProduto) {
		OperatingResult result = new OperatingResult();
		Produto produto = produtoRepository.findOne(idProduto);
		
		result.add(linkTo(methodOn(ProdutoController.class).getProduto(produto.getIdProduto())).withRel("Consultar Produto"));
		result.add(linkTo(methodOn(ProdutoController.class).update(produto.getIdProduto(), produto.getDsProduto())).withRel("Alterar Produto"));
		result.add(linkTo(methodOn(ProdutoController.class).remove(produto.getIdProduto())).withRel("Remover Produto"));
		result.add(linkTo(methodOn(ProdutoController.class).getProdutos()).withRel("Consultar todos os Produtos"));

		return produto;

	}

	@RequestMapping(path = "/getProdutos", method = RequestMethod.GET)
	public List<Produto> getProdutos() {
		OperatingResult result = new OperatingResult();
		List<Produto> produtos = produtoRepository.findAll();
		for (Produto produto : produtos) {
			result.add(linkTo(methodOn(ProdutoController.class).getProduto(produto.getIdProduto())).withRel("Consultar Produto"));
			result.add(linkTo(methodOn(ProdutoController.class).update(produto.getIdProduto(), produto.getDsProduto())).withRel("Alterar Produto"));
			result.add(linkTo(methodOn(ProdutoController.class).remove(produto.getIdProduto())).withRel("Remover Produto"));
		}
		return produtos;
	}
}
