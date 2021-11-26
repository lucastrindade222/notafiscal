package br.com.lucas.notafiscal.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.lucas.notafiscal.domain.Status;
import br.com.lucas.notafiscal.repository.StatusRepository;

@Service
public class HtmlService {
	
	@Value("${api.teste}")
	private String url;
	@Value("${api.uri}")
	private String uri1;
	@Value("${api.userAgunt}")
	private String userAgunt;
//	@Autowired
//	 
//	private StatusRepository repo;

	public void get() {

		try {

			Status obj = new Status();

			// Response response = Jsoup.connect(url).followRedirects(false).execute();
			// System.out.println(" "+response.header("location"));

			final Document documeto = Jsoup.connect(this.url).followRedirects(true).userAgent(userAgunt).timeout(30000)
					.get();
//			 System.out.println(documeto);

			// Map<String, String> cookies = response.cookies();

			// And this is the easieste way I've found to remain in session
			// Document doc = Jsoup.connect("url").cookies(cookies).get();
			int x = 1;
			int a = 1;
			int t = 0;
//			for (Element row : documeto.select("th")) {
//   
//				System.out.println(row.text());
//				x++;
//			}

			// .tabelaListagemDados .linhaImparCentralizada td,
			// .tabelaListagemDadosSemMargem .linhaImparCentralizada td
//tamanho do th colocado dentro do array

			for (Element row : documeto.select("table.tabelaListagemDados td")) {

				if (t >= 9) {
			//		obj = this.incert(obj);
					System.out.println(obj.toString());
					t = 0;
					obj = new Status();
				}

				String ax = documeto.select("th").get(t).text();
				// System.out.println(ax);

				String tex = row.text();
				if (tex.equals("")) {
					Elements au = row.getElementsByTag("img");
					// System.out.println(au.attr("src"));

					this.from(obj, ax, this.verificacao(au.attr("src")));
				} else {

					// System.out.println(row.text());
					this.from(obj, ax, row.text());
				}

				// System.out.println(row.attr("src"));
				t++;
			}

//			for (Element row : documeto.select("img")) {
//				String srcValue = row.attr("src");
//			 
//				if (srcValue.equals("imagens/bola_verde_P.png")) {
//
//					Element aa = documeto.select("table.tabelaListagemDados tr").get(a);
//					 
//						 
//
//					 
//					System.out.println(aa.text());
//					System.out.println("||"+row.text());
//					System.out.println(srcValue);
//					a++;
//					t++;
//					if (t >= 9) {
//						t = 0;
//					}
//					if (a >= 16) {
//						a = 1;
//					}
//					x++;
//				}
//			}

//			colocar dentro de array ir conta pelo index 
//
//
//			System.out.println(documeto.select("img").get(0));
//			Element row = documeto.select("img").get(0);
//						
//			System.out.println(row.attr("src"));
//			

//			
//			System.out.println(documeto.select("img").get(15));
//			Element row = documeto.select("img").get(15);
//			
//			System.out.println(row.attr("src"));

//			for (Element row : documeto.select("img")) {
//
//				 
//				 String srcValue = row.attr("src");
//				 System.out.println(srcValue);
//		 				x++;
//			}
			x = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void from(Status obj, String type, String status) {

		switch (type) {
		case "Autorizador":

			obj.setAutorizador(status);

			break;

		case "Autorização4":

			obj.setAutorizacao4(status);

			break;

		case "Retorno Autorização4":

			obj.setRecepcaoEvento4(status);

			break;

		case "Inutilização4":

			obj.setInutilizacao4(status);
			;

			break;

		case "Consulta Protocolo4":

			obj.setConsultaProtocolo4(status);
			;

		case "Status Serviço4":

			obj.setStatusServico4(status);
			;

			break;
		case "Tempo Médio":

			obj.setTempoMedio(status);
			;

			break;

		case "Consulta Cadastro4":

			obj.setTempoMedio(status);
			;

			break;

		case "Recepção Evento4":

			obj.setTempoMedio(status);
			;

			break;
		default:
			System.out.println("||??" + type);

		}

	}

	public String verificacao(String json) {

		String x = json;
		int indexArroba = x.indexOf("a_");

		int indexEComercial = x.indexOf("_P");
		if (indexArroba != -1 && indexEComercial != -1) {
			String obj = x.substring(indexArroba + 2, indexEComercial);
			// System.out.println(obj);

			return obj;
		} else {
			return null;
		}

	}

//	public Status incert(Status obj) {
//
//		obj = repo.save(obj);
//
//		return obj;
//	}

}
