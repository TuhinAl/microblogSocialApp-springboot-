package com.square.health.util;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 10/2/21 at 1:00 AM
 **/
public interface StaticData {

    // ======================= Admin Service Static Infos =============
    String ADMIN_REGISTER_MESSAGE = "admin Registered successfully";

    String INVALID_BLOGGER_MESSAGE = "Blogger With This ID is not found";

    String BLOGGER_EXIST_MESSAGE = "This Blogger already approved";

    String BLOGGER_APPROVED_BY_ADMIN = "This blogger approved by Admin";

    String BLOGGER_ACTIVATE_OR_DEACTIVATE = "This blogger activated/deactivated by Admin";

    String POST_NOT_EXIST = "Post is not found";

    String POST_APPROVED_MESSAGE = "Post approved by Admin";

    String POST_REMOVED_BY_ADMIN = "Post deleted by Admin";

    //====================== Blogger Service static data =================

    String EMPTY_REGISTRATION_INFO = "Empty registration Infos received";

    String BLOGGER_REGISTRATION_SUCCESS_MESSAGE = "Blogger Registered successfully";

    String INVALID_BLOGGER_ID = "Blogger With This ID is not found";

    String INACTIVE_BLOGGER_API_ACCESS_DENY = "Invalid Blogger try to post a comment";

    String COMMENT_ADDED_TO_POST = "Comment added to the post";

    String STATUS_POSTED = "Status posted successful";

    String POST_UPDATED_MESSAGE = "Post updated successfully";

    String POST_DELETED = "Post deleted successfully";

    String POST_LIKED = "You liked this post";

    String DISLIKED_POST = "You disliked this post";


    // ========== Comment service static data=======================

    String EMPTY_POST_ID = "Empty postId Received";

    String EMPTY_BLOGGER_ID = "Empty bloggerId Received";

    String EMPTY_COMMENT_ID = "Empty commentId received";

    String COMMENT_UPDATED = "Comment updated";

    String COMMENT_DELETED = "completed deleted";


}
