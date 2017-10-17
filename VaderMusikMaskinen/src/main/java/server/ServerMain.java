package server;

import api.MusixMatchAPIClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dataclasses.Track;
import spark.Response;
import spark.Spark.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static spark.Spark.*;


public class ServerMain {

	final String weatherData = "/weatherdatasets/:location";
	final String playlistName ="/playlistName/:weather";
	final String tracks ="/tracks/:weather";
	private ServerController controller;

	public ServerMain(){
		port(7313);
		controller =  new ServerController();

		System.out.println("Server Started: Listening on port 7313");

		before((request, response) ->
				response.header("Access-Control-Allow-Origin", "*"));

		get(weatherData, (request, response) -> {
			String location = request.params(":location");
			String res = controller.getWeatherTranslation(location);
			return res;
		});

		get(weatherData, (request, response) -> {
			String location = request.params(":location");
			String res = controller.getWeatherTranslation(location);
			System.out.println();

			return res;
		});

		get(playlistName, (request, response) -> {
			String weather = request.params(":weather");
			controller.fetchPlaylistByWeather(weather);
			String res = controller.getPlaylistName(weather);
			return res;
		});

		get(tracks, (request, response) -> {
			String weather = request.params(":weather");
			controller.fetchPlaylistByWeather(weather);
			List<Track> res = controller.getTracks(weather);

			Gson gson = new Gson();
			Type type = new TypeToken<LinkedList<Track>>() {}.getType();
			String json = gson.toJson(res, type);
			System.out.println(json);
			return json;
		});
	}




	public static void main(String[] args) {
		new ServerMain();


	}
}
