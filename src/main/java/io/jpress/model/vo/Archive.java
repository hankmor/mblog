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
package io.jpress.model.vo;

import com.jfinal.core.JFinal;
import io.jpress.model.Taxonomy;
import io.jpress.model.router.TaxonomyRouter;

import java.util.ArrayList;
import java.util.List;

/**
 * 存档、归档。 和数据库无关的实体类。
 *
 * @author 杨福海
 */
public class Archive {
    public static final String ARCHIVE_TAXONOMY = "archive";
    private String date; // 日期
    private long count; // 数量
    private List<Object> datas; // 数据列表
    private String url;

    public Archive() {
    }

    public Archive(String date, long count) {
        super();
        this.date = date;
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }

    public void addData(Object o) {
        if (datas == null) {
            datas = new ArrayList<Object>();
        }

        datas.add(o);
    }

    public String getUrl() {
        Taxonomy taxonomy = new Taxonomy();
        taxonomy.setContentModule(ARCHIVE_TAXONOMY);
        taxonomy.setSlug(this.getDate());
        return JFinal.me().getContextPath() + TaxonomyRouter.getRouter(taxonomy);
    }
}
