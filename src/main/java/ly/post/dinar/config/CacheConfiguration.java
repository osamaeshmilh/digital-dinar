package ly.post.dinar.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, ly.post.dinar.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, ly.post.dinar.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, ly.post.dinar.domain.User.class.getName());
            createCache(cm, ly.post.dinar.domain.Authority.class.getName());
            createCache(cm, ly.post.dinar.domain.User.class.getName() + ".authorities");
            createCache(cm, ly.post.dinar.domain.EntityAuditEvent.class.getName());
            createCache(cm, ly.post.dinar.domain.Activation.class.getName());
            createCache(cm, ly.post.dinar.domain.Attachment.class.getName());
            createCache(cm, ly.post.dinar.domain.WalletUser.class.getName());
            createCache(cm, ly.post.dinar.domain.WalletUser.class.getName() + ".walletTransactions");
            createCache(cm, ly.post.dinar.domain.WalletUser.class.getName() + ".beneficiaries");
            createCache(cm, ly.post.dinar.domain.WalletTransaction.class.getName());
            createCache(cm, ly.post.dinar.domain.Beneficiary.class.getName());
            createCache(cm, ly.post.dinar.domain.WalletProfile.class.getName());
            createCache(cm, ly.post.dinar.domain.PaymentMethod.class.getName());
            createCache(cm, ly.post.dinar.domain.Notification.class.getName());
            createCache(cm, ly.post.dinar.domain.ViewLog.class.getName());
            createCache(cm, ly.post.dinar.domain.Setting.class.getName());
            createCache(cm, ly.post.dinar.domain.Slider.class.getName());
            createCache(cm, ly.post.dinar.domain.Country.class.getName());
            createCache(cm, ly.post.dinar.domain.City.class.getName());
            createCache(cm, ly.post.dinar.domain.Category.class.getName());
            createCache(cm, ly.post.dinar.domain.Transaction.class.getName());
            createCache(cm, ly.post.dinar.domain.Transaction.class.getName() + ".walletTransactions");
            createCache(cm, ly.post.dinar.domain.VoucherCompany.class.getName());
            createCache(cm, ly.post.dinar.domain.VoucherType.class.getName());
            createCache(cm, ly.post.dinar.domain.Voucher.class.getName());
            createCache(cm, ly.post.dinar.domain.Bank.class.getName());
            createCache(cm, ly.post.dinar.domain.BankBranch.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
