package br.jus.tst.esocialjt.xml.gerador;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.tst.esocialjt.dominio.Evento;
import br.jus.tst.esocialjt.dominio.TipoEvento;
import br.jus.tst.esocialjt.negocio.exception.GeracaoXmlException;

@Component
public class GeradorXmlFactory {
	@Autowired
	private GeradorXmlInformacaoEmpregador geradorXmlInformacaoEmpregador;

	@Autowired
	private GeradorXmlTabelaEstabelecimento geradorXmlTabelaEstabelecimento;

	@Autowired
	private GeradorXmlTabelaRubrica geradorXmlTabelaRubrica;

	@Autowired
	private GeradorXmlTabelaLotacao geradorXmlTabelaLotacao;

	@Autowired
	private GeradorXmlTabelaCargo geradorXmlTabelaCargo;

	@Autowired
	private GeradorXmlTabelaCarreira geradorXmlTabelaCarreira;

	@Autowired
	private GeradorXmlTabelaFuncao geradorXmlTabelaFuncao;
	
	@Autowired
	private GeradorXmlTabelaHorario geradorXmlTabelaHorario;

	@Autowired
	private GeradorXmlTabelaProcesso geradorXmlTabelaProcesso;

	@Autowired
	private GeradorXmlAdmissao geradorXmlAdmissao;

	private Map<TipoEvento, GeradorXml> geradores;

	public GeradorXml getGerador(Evento evento) throws GeracaoXmlException {
		GeradorXml geradorXml = getGeradores().get(evento.getTipoEvento());
		if (geradorXml == null) {
			throw new GeracaoXmlException(evento, "Não foi possível encontrar um gerador de XML para o evento");
		}
		return geradorXml;
	}

	private Map<TipoEvento, GeradorXml> getGeradores() {
		if (geradores == null) {
			geradores = new HashMap<>();
			geradores.put(TipoEvento.S1000, geradorXmlInformacaoEmpregador);
			geradores.put(TipoEvento.S1005, geradorXmlTabelaEstabelecimento);
			geradores.put(TipoEvento.S1010, geradorXmlTabelaRubrica);
			geradores.put(TipoEvento.S1020, geradorXmlTabelaLotacao);
			geradores.put(TipoEvento.S1030, geradorXmlTabelaCargo);
			geradores.put(TipoEvento.S1035, geradorXmlTabelaCarreira);
			geradores.put(TipoEvento.S1040, geradorXmlTabelaFuncao);
			geradores.put(TipoEvento.S1050, geradorXmlTabelaHorario);
			geradores.put(TipoEvento.S1070, geradorXmlTabelaProcesso);
			geradores.put(TipoEvento.S2200, geradorXmlAdmissao);
		}
		return geradores;
	}

}
