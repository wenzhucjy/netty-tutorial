package com.netty.module.player.dao;

import com.netty.module.player.dao.entity.Player;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * description: 玩家dao
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-28 16:23
 */
@Component
public class PlayerDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    /**
     * 获取玩家通过id
     * 
     * @param playerId 玩家id
     * @return 玩家
     */
    public Player getPlayerById(long playerId) {
        return hibernateTemplate.get(Player.class, playerId);
    }

    /**
     * 获取玩家通过玩家名
     * 
     * @param playerName 玩家名
     * @return 玩家
     */
    public Player getPlayerByName(final String playerName) {

        return hibernateTemplate.execute(new HibernateCallback<Player>() {

            @Override
            public Player doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery("SELECT * FROM player where playerName = ?");
                int q = 0;
                query.setString(q, playerName);
                query.addEntity(Player.class);

                @SuppressWarnings("unchecked")
                List<Player> list = query.list();
                if (list == null || list.isEmpty()) {
                    return null;
                }
                return list.get(0);
            }
        });
    }

    /**
     * 创建玩家
     * 
     * @param player 玩家
     * @return 玩家
     */
    public Player createPlayer(Player player) {
        long playerId = (Long) hibernateTemplate.save(player);
        player.setPlayerId(playerId);
        return player;
    }
}
