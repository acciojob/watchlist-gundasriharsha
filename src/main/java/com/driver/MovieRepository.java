package com.driver;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class MovieRepository {
    private HashMap<String,Movie>movieMap;
    private HashMap<String,Director>directorMap;
    private HashMap<String,List<String>>directorMovieMapping;
    //    pair is:DirectorName,List of Movie names
    public MovieRepository(){
        this.movieMap=new HashMap<String,Movie>();
        this.directorMap=new HashMap<String,Director>();
        this.directorMovieMapping=new HashMap<String,List<String>>();
    }
    public void saveMovie(Movie movie){
        movieMap.put(movie.getName(), movie);
    }
    public void saveDirector(Director director){
        directorMap.put(director.getName(),director);
    }
    public void saveMovieDirectorPair(String movie,String director){
//         if(movieMap.containsKey(movie)&& directorMap.put(director)){
        List<String> currentMoviesByDirector=new ArrayList<>();
        if(directorMovieMapping.containsKey(director))
            currentMoviesByDirector=directorMovieMapping.get(director);
        currentMoviesByDirector.add(movie);
        directorMovieMapping.put(director,currentMoviesByDirector);
    }


    public Movie findMovie(String movie) {
        return movieMap.get(movie);
    }

    public Director findDirector(String director) {
        return directorMap.get(director);
    }
    public List<String> findMoviesFromDirector(String director){
        List<String> moviesList=new ArrayList<>();
        if(directorMovieMapping.containsKey(director)) moviesList=directorMovieMapping.get(director);
        return moviesList;
    }
    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }
    public void deleteDirector(String director){
        List<String> movies=new ArrayList<>();
        if(directorMovieMapping.containsKey(director)){
            movies=directorMovieMapping.get(director);
            for(String movie:movies){
                if(movieMap.containsKey(movie)){
                    movieMap.remove(movie);
                }
            }
            directorMovieMapping.remove(director);
        }
        if(directorMap.containsKey(director));
        directorMap.remove(director);
    }

    public List<String> getAllDirectors() {
        return new ArrayList<>(directorMap.keySet());
    }

    public void removeDirector(String director) {
        directorMap.remove(director);
        directorMovieMapping.remove(director);
    }
    public void removeMovie(String movieNme) {
        movieMap.remove(movieNme);
    }
}