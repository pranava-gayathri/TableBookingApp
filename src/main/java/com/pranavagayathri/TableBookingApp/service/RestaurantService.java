package com.pranavagayathri.TableBookingApp.service;

import com.pranavagayathri.TableBookingApp.dto.RatingAndReviewsDTO;
import com.pranavagayathri.TableBookingApp.dto.ReservationDTO;
import com.pranavagayathri.TableBookingApp.dto.RestaurantDTO;
import com.pranavagayathri.TableBookingApp.dto.TableDTO;
import com.pranavagayathri.TableBookingApp.exceptions.RestaurantNotFoundException;
import com.pranavagayathri.TableBookingApp.model.RatingsAndReviews;
import com.pranavagayathri.TableBookingApp.model.Restaurant;
import com.pranavagayathri.TableBookingApp.model.Tables;
import com.pranavagayathri.TableBookingApp.repository.RatingAndReviewsRepository;
import com.pranavagayathri.TableBookingApp.repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepo repo;

    @Autowired
    private RatingAndReviewsRepository ratingAndReviewsRepository;



    public List<RestaurantDTO> getAllRestaurants(){
        List<RestaurantDTO> restaurantDTOList=new ArrayList<>();
        List<Restaurant> restaurants=repo.findAll();
        restaurantDTOList(restaurants, restaurantDTOList);
        List<RatingsAndReviews> ratingsAndReviews=ratingAndReviewsRepository.findAll();
        List<RatingAndReviewsDTO> ratingAndReviewsDTOList=new ArrayList<>();
        for(RatingsAndReviews rr:ratingsAndReviews){
            RatingAndReviewsDTO rrdto=new RatingAndReviewsDTO();
            rrdto.setRating(rr.getRating());
            rrdto.setReview(rr.getReview());
            ratingAndReviewsDTOList.add(rrdto);
        }


        return restaurantDTOList;
    }
    public RestaurantDTO getRestaurantById(long id) throws RestaurantNotFoundException {
        Optional<Restaurant> r1=repo.findById(id);

        if(!r1.isPresent())
        {
            throw new RestaurantNotFoundException("restaurant not found");
        }
        else{

        Restaurant r=repo.findById(id).get();
        RestaurantDTO rdto=new RestaurantDTO();
        rdto.setRestaurantId(r.getRestaurantId());
        rdto.setRestaurantName(r.getRestaurantName());
        rdto.setRestaurantLocation(r.getRestaurantLocation());
        rdto.setRestaurantDescription(r.getRestaurantDescription());
        rdto.setRestaurantTotalTableCount(r.getRestaurantTotalTableCount());

        List<TableDTO> tableDTOList=new ArrayList<>();
        List<Tables> tables=r.getTables();
        tableDTOList(tables, tableDTOList);
        rdto.setTableDTOList(tableDTOList);

        List<RatingAndReviewsDTO> ratingAndReviewsDTOS=new ArrayList<>();
        List<RatingsAndReviews> ratingsAndReviews=r.getRatingsAndReviews();
        ratingAndReviewsDTOList(ratingsAndReviews,ratingAndReviewsDTOS);
        rdto.setRatingAndReviewsDTOS(ratingAndReviewsDTOS);

        return rdto;}
    }

    private static void restaurantDTOList(List<Restaurant> restaurants, List<RestaurantDTO> restaurantDTOList) {
        for(Restaurant r: restaurants){
            RestaurantDTO rdto=new RestaurantDTO();
            rdto.setRestaurantId(r.getRestaurantId());
            rdto.setRestaurantName(r.getRestaurantName());
            rdto.setRestaurantLocation(r.getRestaurantLocation());
            rdto.setRestaurantDescription(r.getRestaurantDescription());
            rdto.setRestaurantTotalTableCount(r.getRestaurantTotalTableCount());

            List<TableDTO> tableDTOList=new ArrayList<>();
            List<Tables> tables=r.getTables();
            tableDTOList(tables, tableDTOList);
            rdto.setTableDTOList(tableDTOList);

            List<RatingAndReviewsDTO> ratingAndReviewsDTOS=new ArrayList<>();
            List<RatingsAndReviews> ratingsAndReviews=r.getRatingsAndReviews();
            ratingAndReviewsDTOList(ratingsAndReviews,ratingAndReviewsDTOS);
            rdto.setRatingAndReviewsDTOS(ratingAndReviewsDTOS);

            restaurantDTOList.add(rdto);



        }
    }

    private static void tableDTOList(List<Tables> tables, List<TableDTO> tableDTOList) {
        for(Tables t: tables){
            TableDTO tdto=new TableDTO();
            tdto.setTotalSeats(t.getTotalSeats());
            tdto.setTableNumber(t.getTableNumber());
            tdto.setTableId(t.getTableId());
            tableDTOList.add(tdto);
        }
    }
    private static void ratingAndReviewsDTOList(List<RatingsAndReviews> ratingsAndReviews, List<RatingAndReviewsDTO> ratingAndReviewsDTOList) {
        for(RatingsAndReviews rr: ratingsAndReviews){
            RatingAndReviewsDTO rrdto =new RatingAndReviewsDTO();
            rrdto.setReview(rr.getReview());
            rrdto.setRating(rr.getRating());
            ratingAndReviewsDTOList.add(rrdto);

        }
    }

    public List<TableDTO> getTablesByRestaurantId(long id){
        Restaurant restaurant=repo.findById(id).orElseThrow();
        List<TableDTO> tableDTOList=new ArrayList<>();
        List<Tables> tables=restaurant.getTables();
        tableDTOList(tables, tableDTOList);


        return  tableDTOList;

    }


    public void addRestaurant(Restaurant restaurant) {
        repo.save(restaurant);
    }
}
