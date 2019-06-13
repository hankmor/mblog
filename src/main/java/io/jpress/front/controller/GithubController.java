/**
 * Copyright (c) 2015-2016, Michael Yang 杨福海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jpress.front.controller;

import com.alibaba.fastjson.JSONObject;
import io.jpress.core.BaseFrontController;
import io.jpress.core.cache.ActionCacheManager;
import io.jpress.model.Comment;
import io.jpress.model.Content;
import io.jpress.model.query.CommentQuery;
import io.jpress.model.query.ContentQuery;
import io.jpress.router.RouterMapping;
import io.jpress.router.RouterNotAllowConvert;
import io.jpress.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.Date;

/**
 * 从github回调的信息，主要用于获取gittalk的评论。
 *
 * @author belonk
 */
@RouterMapping(url = "/github")
@RouterNotAllowConvert
public class GithubController extends BaseFrontController {

    private static final String SECRET = "BlogOfBelonk";

    public void index() {
        renderError(404);
    }

    public void webhook() throws Exception {
        // TODO 验证签名

        HttpServletRequest request = getRequest();
        String event = request.getHeader("X-GitHub-Event");
        String delivery = request.getHeader("X-GitHub-Delivery");
        String sign = request.getHeader("X-Hub-Signature");

        // {"issue":{"comments":1,"assignees":[],"created_at":"2019-06-05T11:33:16Z","title":"Spring AMQP简介和使用 - IT技术博客","body":"https://blog.belonk.com/c/spring_amqp.html \n\n Spring AMQP 是 Spring 对 AMQP 协议的封装和扩展，提供了消息发送和接收的模板。Spring AMQP由spring-amqp和spring-rabbit两个模块组成。spring-amqp模块位于org.springframework.amqp.core包，它的目标是提供不依赖于任何特定AMQP代理实现或客户端库的通用抽象；而spring-rabbit是spring-amqp通用抽象的具体实现，目前仅提供了rabbitmq的实现。\n","url":"https://api.github.com/repos/belonk/mblog-comments/issues/86","labels":[{"default":false,"color":"ededed","name":"/c/spring_amqp.html","id":1392138674,"url":"https://api.github.com/repos/belonk/mblog-comments/labels//c/spring_amqp.html","node_id":"MDU6TGFiZWwxMzkyMTM4Njc0"},{"default":false,"color":"ededed","name":"Gitalk","id":947692669,"url":"https://api.github.com/repos/belonk/mblog-comments/labels/Gitalk","node_id":"MDU6TGFiZWw5NDc2OTI2Njk="}],"labels_url":"https://api.github.com/repos/belonk/mblog-comments/issues/86/labels{/name}","author_association":"OWNER","number":86,"updated_at":"2019-06-13T13:34:04Z","events_url":"https://api.github.com/repos/belonk/mblog-comments/issues/86/events","html_url":"https://github.com/belonk/mblog-comments/issues/86","comments_url":"https://api.github.com/repos/belonk/mblog-comments/issues/86/comments","repository_url":"https://api.github.com/repos/belonk/mblog-comments","id":452452171,"state":"open","locked":false,"user":{"gists_url":"https://api.github.com/users/belonk/gists{/gist_id}","repos_url":"https://api.github.com/users/belonk/repos","following_url":"https://api.github.com/users/belonk/following{/other_user}","starred_url":"https://api.github.com/users/belonk/starred{/owner}{/repo}","login":"belonk","followers_url":"https://api.github.com/users/belonk/followers","type":"User","url":"https://api.github.com/users/belonk","subscriptions_url":"https://api.github.com/users/belonk/subscriptions","received_events_url":"https://api.github.com/users/belonk/received_events","avatar_url":"https://avatars1.githubusercontent.com/u/6100874?v=4","events_url":"https://api.github.com/users/belonk/events{/privacy}","html_url":"https://github.com/belonk","site_admin":false,"id":6100874,"gravatar_id":"","node_id":"MDQ6VXNlcjYxMDA4NzQ=","organizations_url":"https://api.github.com/users/belonk/orgs"},"node_id":"MDU6SXNzdWU0NTI0NTIxNzE="},"sender":{"gists_url":"https://api.github.com/users/belonk/gists{/gist_id}","repos_url":"https://api.github.com/users/belonk/repos","following_url":"https://api.github.com/users/belonk/following{/other_user}","starred_url":"https://api.github.com/users/belonk/starred{/owner}{/repo}","login":"belonk","followers_url":"https://api.github.com/users/belonk/followers","type":"User","url":"https://api.github.com/users/belonk","subscriptions_url":"https://api.github.com/users/belonk/subscriptions","received_events_url":"https://api.github.com/users/belonk/received_events","avatar_url":"https://avatars1.githubusercontent.com/u/6100874?v=4","events_url":"https://api.github.com/users/belonk/events{/privacy}","html_url":"https://github.com/belonk","site_admin":false,"id":6100874,"gravatar_id":"","node_id":"MDQ6VXNlcjYxMDA4NzQ=","organizations_url":"https://api.github.com/users/belonk/orgs"},"action":"created","comment":{"author_association":"OWNER","issue_url":"https://api.github.com/repos/belonk/mblog-comments/issues/86","updated_at":"2019-06-13T13:34:04Z","html_url":"https://github.com/belonk/mblog-comments/issues/86#issuecomment-501704977","created_at":"2019-06-13T13:34:04Z","id":501704977,"body":"test\r\n\r\n> goodtest\r\n\r\nhaha, it's good","user":{"gists_url":"https://api.github.com/users/belonk/gists{/gist_id}","repos_url":"https://api.github.com/users/belonk/repos","following_url":"https://api.github.com/users/belonk/following{/other_user}","starred_url":"https://api.github.com/users/belonk/starred{/owner}{/repo}","login":"belonk","followers_url":"https://api.github.com/users/belonk/followers","type":"User","url":"https://api.github.com/users/belonk","subscriptions_url":"https://api.github.com/users/belonk/subscriptions","received_events_url":"https://api.github.com/users/belonk/received_events","avatar_url":"https://avatars1.githubusercontent.com/u/6100874?v=4","events_url":"https://api.github.com/users/belonk/events{/privacy}","html_url":"https://github.com/belonk","site_admin":false,"id":6100874,"gravatar_id":"","node_id":"MDQ6VXNlcjYxMDA4NzQ=","organizations_url":"https://api.github.com/users/belonk/orgs"},"url":"https://api.github.com/repos/belonk/mblog-comments/issues/comments/501704977","node_id":"MDEyOklzc3VlQ29tbWVudDUwMTcwNDk3Nw=="},"repository":{"stargazers_count":0,"pushed_at":"2018-05-29T03:42:56Z","subscription_url":"https://api.github.com/repos/belonk/mblog-comments/subscription","branches_url":"https://api.github.com/repos/belonk/mblog-comments/branches{/branch}","issue_comment_url":"https://api.github.com/repos/belonk/mblog-comments/issues/comments{/number}","labels_url":"https://api.github.com/repos/belonk/mblog-comments/labels{/name}","subscribers_url":"https://api.github.com/repos/belonk/mblog-comments/subscribers","releases_url":"https://api.github.com/repos/belonk/mblog-comments/releases{/id}","svn_url":"https://github.com/belonk/mblog-comments","id":135237842,"forks":0,"archive_url":"https://api.github.com/repos/belonk/mblog-comments/{archive_format}{/ref}","git_refs_url":"https://api.github.com/repos/belonk/mblog-comments/git/refs{/sha}","forks_url":"https://api.github.com/repos/belonk/mblog-comments/forks","statuses_url":"https://api.github.com/repos/belonk/mblog-comments/statuses/{sha}","ssh_url":"git@github.com:belonk/mblog-comments.git","full_name":"belonk/mblog-comments","size":0,"languages_url":"https://api.github.com/repos/belonk/mblog-comments/languages","html_url":"https://github.com/belonk/mblog-comments","collaborators_url":"https://api.github.com/repos/belonk/mblog-comments/collaborators{/collaborator}","clone_url":"https://github.com/belonk/mblog-comments.git","name":"mblog-comments","pulls_url":"https://api.github.com/repos/belonk/mblog-comments/pulls{/number}","default_branch":"master","hooks_url":"https://api.github.com/repos/belonk/mblog-comments/hooks","trees_url":"https://api.github.com/repos/belonk/mblog-comments/git/trees{/sha}","tags_url":"https://api.github.com/repos/belonk/mblog-comments/tags","private":false,"contributors_url":"https://api.github.com/repos/belonk/mblog-comments/contributors","has_downloads":true,"notifications_url":"https://api.github.com/repos/belonk/mblog-comments/notifications{?since,all,participating}","open_issues_count":85,"created_at":"2018-05-29T03:42:56Z","watchers":0,"keys_url":"https://api.github.com/repos/belonk/mblog-comments/keys{/key_id}","deployments_url":"https://api.github.com/repos/belonk/mblog-comments/deployments","has_projects":true,"archived":false,"has_wiki":true,"updated_at":"2018-05-29T03:42:56Z","comments_url":"https://api.github.com/repos/belonk/mblog-comments/comments{/number}","stargazers_url":"https://api.github.com/repos/belonk/mblog-comments/stargazers","disabled":false,"git_url":"git://github.com/belonk/mblog-comments.git","has_pages":false,"owner":{"gists_url":"https://api.github.com/users/belonk/gists{/gist_id}","repos_url":"https://api.github.com/users/belonk/repos","following_url":"https://api.github.com/users/belonk/following{/other_user}","starred_url":"https://api.github.com/users/belonk/starred{/owner}{/repo}","login":"belonk","followers_url":"https://api.github.com/users/belonk/followers","type":"User","url":"https://api.github.com/users/belonk","subscriptions_url":"https://api.github.com/users/belonk/subscriptions","received_events_url":"https://api.github.com/users/belonk/received_events","avatar_url":"https://avatars1.githubusercontent.com/u/6100874?v=4","events_url":"https://api.github.com/users/belonk/events{/privacy}","html_url":"https://github.com/belonk","site_admin":false,"id":6100874,"gravatar_id":"","node_id":"MDQ6VXNlcjYxMDA4NzQ=","organizations_url":"https://api.github.com/users/belonk/orgs"},"commits_url":"https://api.github.com/repos/belonk/mblog-comments/commits{/sha}","compare_url":"https://api.github.com/repos/belonk/mblog-comments/compare/{base}...{head}","git_commits_url":"https://api.github.com/repos/belonk/mblog-comments/git/commits{/sha}","blobs_url":"https://api.github.com/repos/belonk/mblog-comments/git/blobs{/sha}","git_tags_url":"https://api.github.com/repos/belonk/mblog-comments/git/tags{/sha}","merges_url":"https://api.github.com/repos/belonk/mblog-comments/merges","downloads_url":"https://api.github.com/repos/belonk/mblog-comments/downloads","has_issues":true,"url":"https://api.github.com/repos/belonk/mblog-comments","contents_url":"https://api.github.com/repos/belonk/mblog-comments/contents/{+path}","milestones_url":"https://api.github.com/repos/belonk/mblog-comments/milestones{/number}","teams_url":"https://api.github.com/repos/belonk/mblog-comments/teams","fork":false,"issues_url":"https://api.github.com/repos/belonk/mblog-comments/issues{/number}","events_url":"https://api.github.com/repos/belonk/mblog-comments/events","issue_events_url":"https://api.github.com/repos/belonk/mblog-comments/issues/events{/number}","assignees_url":"https://api.github.com/repos/belonk/mblog-comments/assignees{/user}","open_issues":85,"watchers_count":0,"node_id":"MDEwOlJlcG9zaXRvcnkxMzUyMzc4NDI=","forks_count":0}}
        String json = getRequestObject(String.class);

        if (!"issue_comment".equals(event) || StringUtils.isBlank(json)) {
            renderAjaxResult("Event not supported", 500);
            return;
        }

        JSONObject jsonObject = JSONObject.parseObject(json);
        String action = (String) jsonObject.get("action");
        // 获取评论人信息
        JSONObject commentMap = (JSONObject) jsonObject.get("comment");
        BigInteger commentId = new BigInteger(commentMap.get("id").toString());
        String status = Comment.STATUS_NORMAL;
        if ("deleted".equals(action)) {
            status = Comment.STATUS_DELETE;
        }

        String commentText = commentMap.get("body").toString();
        String ip = getIPAddress();
        String agent = getUserAgent();

        Comment comment = CommentQuery.me().findById(commentId);
        if (comment != null) {
            comment.setText(commentText);
            comment.setIp(ip);
            comment.setAgent(agent);
            comment.setStatus(status);
            if (comment.update()) {
                ActionCacheManager.clearCache();
            }
        } else {
            JSONObject issue = (JSONObject) jsonObject.get("issue");
            // 根据title来查找文章
            String title = (String) issue.get("title");
            title = title.replace(" - IT技术博客", "");
            Content content = ContentQuery.me().findFirstByModuleAndTitle("article", title);
            if (content == null) {
                renderAjaxResult("Content not be found.", 500);
                return;
            }

            JSONObject userMap = (JSONObject) commentMap.get("user");
            String author = (String) userMap.get("login");
            // String headPic = (String) userMap.get("avatar_url");
            String homePage = (String) userMap.get("html_url");
            BigInteger userId = new BigInteger(userMap.get("id").toString());

            comment = new Comment();
            comment.setId(commentId);
            comment.setCreated(new Date());
            comment.setContentModule(content.getModule());
            comment.setType(Comment.TYPE_COMMENT);
            comment.setContentId(content.getId());
            comment.setText(commentText);
            comment.setIp(ip);
            comment.setAgent(agent);
            comment.setAuthor(author);
            // 邮件设置为首页
            comment.setEmail(homePage);
            comment.setStatus(status);
            comment.setUserId(userId);
            comment.setParentId(null);
            if (comment.save()) {
                ActionCacheManager.clearCache();
            }
        }
        renderAjaxResultForSuccess();
    }
}
