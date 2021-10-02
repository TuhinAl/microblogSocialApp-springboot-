package com.square.health.service.serviceImplement;

import com.square.health.dto.AdminDTO;
import com.square.health.mapper.AdminMapper;
import com.square.health.model.Blogger;
import com.square.health.model.Post;
import com.square.health.model.Role;
import com.square.health.repository.AdminRepository;
import com.square.health.repository.BloggerRepository;
import com.square.health.repository.PostRepository;
import com.square.health.service.AdminService;
import com.square.health.util.error_handle.BadRequestException;
import com.square.health.util.error_handle.ErrorDetails;
import com.square.health.util.error_handle.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

import static com.square.health.util.StaticData.*;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 1:04 AM
 **/

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    BloggerRepository bloggerRepository;

    @Autowired
    PostRepository postRepository;

    /**
     * ============= Special Notes =================
     *
     * (1) I used custom mapper to save users (Admin, Blogger)
     * (2) Used @Transactional annotation for successful transaction,
     *      if any transaction (execution) fails, it will rollback to consistent state
     * (3) Threw Exception for every 'Bad' request and if not found
     *      any requested resource in database
     *
     *  (4) Returned standard Response that is also customised.
     *
     *   (5) Though I know Constructor injection is better than field injection
     *       but i used it to get optimal output without any complexity and short period of time
     *
      *
     */


    @Override
    public ResponseEntity<ErrorDetails> registerAdmin(AdminDTO admin) {


        /**
         * created a custom mapper to save admin user into database.
         * the default Role is 'ADMIN' for admin user.
         *
         */
        adminRepository.save(adminMapper.registerMap(admin));

        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                ADMIN_REGISTER_MESSAGE, System.currentTimeMillis()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ErrorDetails> approveBlogger(Long adminId, Long bloggerId) {

        Blogger blogger = bloggerRepository.getById(bloggerId);
        if (blogger.getId() == null) {

            throw new ResourceNotFoundException(INVALID_BLOGGER_MESSAGE);
        }

        /**
         *  When a BLOGGER registered, by default the account will be inactive, and
         *  every account need to be approved by ADMIN user
         *
         *  I set isActive = 0 before approved by admin [Note: active = 1, inactive = 0]
         *  when Admin hit this API, isActive value update to 1,
         *  and adminId set in 'approvedBy' column.
         *  Also, default value of createDate is Current date (GMT), Role = BLOGGER
         *  assign to respective column
         */
        if (blogger.getApproved().equals(0)) {
            blogger.setApproved(1);
            blogger.setApprovedBy(adminId);
            blogger.setIsActive(1);
            blogger.setCreateDate(new Date());
            blogger.setRole(Role.BLOGGER);

        } else {
            throw new BadRequestException(BLOGGER_EXIST_MESSAGE);
        }

        bloggerRepository.save(blogger);


        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                BLOGGER_APPROVED_BY_ADMIN, System.currentTimeMillis()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ErrorDetails> deactivateBlogger(Long bloggerId, Integer status) {

        Blogger blogger = bloggerRepository.getById(bloggerId);

        if (blogger.getId() == null) {
            throw new ResourceNotFoundException(INVALID_BLOGGER_MESSAGE);
        }
        /**
         * Deactivate blogger by set 'isActive' column = 0
         * Activate blogger by set 'isActive' column = 1
         */
        if (status == 0) {
            blogger.setIsActive(1);
        } else if (status == 1) {
            blogger.setIsActive(0);
        }
        bloggerRepository.save(blogger);


        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                BLOGGER_ACTIVATE_OR_DEACTIVATE, System.currentTimeMillis()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ErrorDetails> approveBlogPost(Long adminId, Long postId) {

        Post post = postRepository.getById(postId);

        if (post.getId() == null) {

            throw new ResourceNotFoundException(POST_NOT_EXIST);
        }
        /**
         * Every Status needs to be approved by Admin before posted,
         * set 'isPublished' = 1 and adminId in respective column to track
         * who(admin)  approved this post
         */
        post.setIsPublished(1);
        post.setApprovedBy(adminId);
        postRepository.save(post);


        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                POST_APPROVED_MESSAGE, System.currentTimeMillis()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ErrorDetails> deleteBlogPost(Long postId) {

        Post post = postRepository.getById(postId);

        if (post.getId() == null) {
            throw new ResourceNotFoundException(POST_NOT_EXIST);
        }
        postRepository.delete(post);


        return new ResponseEntity<ErrorDetails>(new ErrorDetails(HttpStatus.OK.value(),
                POST_REMOVED_BY_ADMIN, System.currentTimeMillis()),
                HttpStatus.OK);
    }
}
