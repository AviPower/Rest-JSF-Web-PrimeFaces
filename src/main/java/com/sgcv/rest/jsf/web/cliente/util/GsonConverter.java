package com.sgcv.rest.jsf.web.cliente.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
