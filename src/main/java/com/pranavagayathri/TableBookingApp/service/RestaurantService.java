package com.pranavagayathri.TableBookingApp.service;

import com.pranavagayathri.TableBookingApp.dto.RatingAndReviewsDTO;
import com.pranavagayathri.TableBookingApp.dto.ReservationDTO;
import com.pranavagayathri.TableBookingApp.dto.RestaurantDTO;
import com.pranavagayathri.TableBookingApp.dto.TableDTO;
import com.pranavagayathri.TableBookingApp.exceptions.CannotAddRestaurantException;
import com.pranavagayathri.TableBookingApp.exceptions.RestaurantNotFoundException;
import com.pranavagayathri.TableBookingApp.helpermethods.EntityToDTO;
import com.pranavagayathri.TableBookingApp.helpermethods.TableToTableDTO;
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
public class RestaurantService implements RestaurantServiceInterface {
    @Autowired
    private RestaurantRepo repo;

    @Autowired
    private RatingAndReviewsRepository ratingAndReviewsRepository;

    @Autowired
    private EntityToDTO entityToDTO;

    @Autowired
    private TableToTableDTO tableToTableDTO;


    //@Override
    @Override
    public List<RestaurantDTO> getAllRestaurants() throws RestaurantNotFoundException {
        try {
            List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
            List<Restaurant> restaurants = repo.findAll();
            for(Restaurant r :restaurants){
                RestaurantDTO restaurantDTO=entityToDTO.restaurantToRestaurantDTO(r);
                restaurantDTOList.add(restaurantDTO);
            }


            return restaurantDTOList;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    //@Override
    @Override
    public RestaurantDTO getRestaurantById(long id) throws RestaurantNotFoundException {
        Restaurant restaurant=repo.findById(id).orElse(null);
        if(restaurant!=null)
        {RestaurantDTO restaurantDTO=entityToDTO.restaurantToRestaurantDTO(restaurant);
        return restaurantDTO;}
        else{
            throw new RestaurantNotFoundException("restaurant not found");
        }
    }



    //@Override
    @Override
    public List<TableDTO> getTablesByRestaurantId(long id) {
        Restaurant restaurant = repo.findById(id).orElseThrow();
        List<Tables> tables = restaurant.getTables();
        List<TableDTO> tableDTOList = new ArrayList<>();

        for(Tables t:tables){
            TableDTO tdto= tableToTableDTO.tableDTOList(t);
            tableDTOList.add(tdto);
        }
        return tableDTOList;

    }


    //@Override

    @Override
    public void addRestaurant(Restaurant restaurant) throws CannotAddRestaurantException {
        try {
            repo.save(restaurant);
        } catch (Exception e) {
            throw new CannotAddRestaurantException("provide valid details..");
        }

    }
}
