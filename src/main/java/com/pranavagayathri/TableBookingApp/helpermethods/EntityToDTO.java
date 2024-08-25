package com.pranavagayathri.TableBookingApp.helpermethods;

import com.pranavagayathri.TableBookingApp.dto.*;

import com.pranavagayathri.TableBookingApp.exceptions.UserNotFoundException;
import com.pranavagayathri.TableBookingApp.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityToDTO {

    public UserDTO userToUserDTO(User user) throws UserNotFoundException {
        if(user!=null) {
            UserDTO udto = new UserDTO();
            udto.setUserName(user.getUserName());
            udto.setPhone(user.getPhone());
            udto.setUserId(user.getUserId());
            return udto;
        }
        else{
            throw new UserNotFoundException("user details are invalid");
        }
    }

    public RestaurantDTO restaurantToRestaurantDTO(Restaurant restaurant) {
        RestaurantDTO rdto = new RestaurantDTO();
        rdto.setRestaurantId(restaurant.getRestaurantId());
        rdto.setRestaurantName(restaurant.getRestaurantName());
        rdto.setRestaurantLocation(restaurant.getRestaurantLocation());
        rdto.setRestaurantDescription(restaurant.getRestaurantDescription());
        rdto.setRestaurantTotalTableCount(restaurant.getRestaurantTotalTableCount());
        List<Tables> tables = restaurant.getTables();
        List<TableDTO> tableDTOS = new ArrayList<>();
        TableToTableDTO tableDTO = new TableToTableDTO();
        for (Tables t : tables) {

            TableDTO dto = tableDTO.tableDTOList(t);
            tableDTOS.add(dto);

        }
        rdto.setTableDTOList(tableDTOS);

        List<RatingsAndReviews> ratingsAndReviews = restaurant.getRatingsAndReviews();
        List<RatingAndReviewsDTO> ratingAndReviewsDTOS=new ArrayList<>();
        RatingReviewsToRatingReviewsDTO rrdto=new RatingReviewsToRatingReviewsDTO();
        for (RatingsAndReviews rr :ratingsAndReviews) {

            RatingAndReviewsDTO rrToDto=rrdto.ratingAndReviewsDTO(rr);
            ratingAndReviewsDTOS.add(rrToDto);

        }
        rdto.setRatingAndReviewsDTOS(ratingAndReviewsDTOS);

        return rdto;



    }



}
