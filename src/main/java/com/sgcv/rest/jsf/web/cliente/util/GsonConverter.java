package com.sgcv.rest.jsf.web.cliente.util;

/**
 *
 * @author alvarenga
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Date;

/**
 * Singleton instance of Gson.
 *https://github.com/primefaces-extensions
 * @author  Oleg Varaksin / last modified by $Author$
 * @version $Revision$
 * @since   0.2
 */
/**
 * Gson (también conocido como Google Gson) es una biblioteca de código abierto para el
 * lenguaje de programación Java que permite la serialización y deserialización entre 
 * objetos Java y su representación en notación JSON.
 * Permite la conversión entre objetos Java y JSON de una manera sencilla,
 * simplemente invocando los métodos toJson() o fromJson().
 **/
public final class GsonConverter {

	private static final GsonConverter INSTANCE = new GsonConverter();
	private Gson gson;

	private GsonConverter() {
		GsonBuilder gsonBilder = new GsonBuilder();

		gsonBilder.registerTypeAdapter(Date.class, new DateTypeAdapter());
		gsonBilder.serializeNulls();

		gson = gsonBilder.create();
	}

	public static Gson getGson() {
		return INSTANCE.gson;
	}
}
