# 🚀 Deployment Guide - BlueLock Logistics

## Production Deployment Checklist

### Pre-Deployment
- [ ] All features tested
- [ ] No console errors
- [ ] Database backup created
- [ ] Environment variables configured
- [ ] SSL certificates ready
- [ ] Domain name configured

## 1. Backend Deployment (Spring Boot)

### Option A: JAR Deployment

#### Build the Application
```bash
cd bluelock-logistics/backend
./mvnw clean package -DskipTests
```

#### Run the JAR
```bash
java -jar target/bluelock-logistics-0.0.1-SNAPSHOT.jar
```

#### Run with Custom Port
```bash
java -jar -Dserver.port=8080 target/bluelock-logistics-0.0.1-SNAPSHOT.jar
```

#### Run as Background Service (Linux)
```bash
nohup java -jar target/bluelock-logistics-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
```

### Option B: Docker Deployment

#### Create Dockerfile (backend)
```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/bluelock-logistics-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

#### Build Docker Image
```bash
docker build -t bluelock-backend .
```

#### Run Docker Container
```bash
docker run -d -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/logistics_db \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=password \
  --name bluelock-backend \
  bluelock-backend
```

### Option C: Cloud Deployment

#### AWS Elastic Beanstalk
1. Install AWS CLI and EB CLI
2. Initialize EB application
```bash
eb init -p java-17 bluelock-backend
```
3. Create environment
```bash
eb create bluelock-production
```
4. Deploy
```bash
eb deploy
```

#### Heroku
1. Create Heroku app
```bash
heroku create bluelock-backend
```
2. Add MySQL addon
```bash
heroku addons:create jawsdb:kitefin
```
3. Deploy
```bash
git push heroku main
```

### Production Configuration

#### application-prod.properties
```properties
# Server
server.port=${PORT:8080}

# Database
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=validate

# Security
jwt.secret=${JWT_SECRET}
jwt.expiration=86400000

# CORS
cors.allowed-origins=${FRONTEND_URL}

# Logging
logging.level.root=INFO
logging.file.name=logs/application.log
```

## 2. Frontend Deployment (React)

### Build for Production

#### Update API URL
Edit `src/services/api.js`:
```javascript
const API_BASE_URL = process.env.REACT_APP_API_URL || 'https://api.yourdomain.com/api';
```

#### Create .env.production
```env
REACT_APP_API_URL=https://api.yourdomain.com/api
REACT_APP_NAME=BlueLock Logistics
```

#### Build the Application
```bash
cd bluelock-logistics/frontend
npm run build
```

This creates a `build` folder with optimized production files.

### Option A: Static Hosting (Netlify)

#### Deploy to Netlify
1. Install Netlify CLI
```bash
npm install -g netlify-cli
```

2. Deploy
```bash
netlify deploy --prod --dir=build
```

#### Configure Redirects
Create `public/_redirects`:
```
/*    /index.html   200
```

### Option B: Nginx Deployment

#### Install Nginx
```bash
sudo apt update
sudo apt install nginx
```

#### Configure Nginx
Create `/etc/nginx/sites-available/bluelock`:
```nginx
server {
    listen 80;
    server_name yourdomain.com;
    root /var/www/bluelock/build;
    index index.html;

    location / {
        try_files $uri $uri/ /index.html;
    }

    location /api {
        proxy_pass http://localhost:8080;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection 'upgrade';
        proxy_set_header Host $host;
        proxy_cache_bypass $http_upgrade;
    }
}
```

#### Enable Site
```bash
sudo ln -s /etc/nginx/sites-available/bluelock /etc/nginx/sites-enabled/
sudo nginx -t
sudo systemctl restart nginx
```

#### Copy Build Files
```bash
sudo mkdir -p /var/www/bluelock
sudo cp -r build/* /var/www/bluelock/
```

### Option C: Docker Deployment

#### Create Dockerfile (frontend)
```dockerfile
FROM node:18-alpine as build
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build

FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
```

#### Create nginx.conf
```nginx
server {
    listen 80;
    location / {
        root /usr/share/nginx/html;
        index index.html;
        try_files $uri $uri/ /index.html;
    }
}
```

#### Build and Run
```bash
docker build -t bluelock-frontend .
docker run -d -p 80:80 bluelock-frontend
```

### Option D: Vercel Deployment

1. Install Vercel CLI
```bash
npm install -g vercel
```

2. Deploy
```bash
vercel --prod
```

## 3. Database Deployment

### Option A: Managed MySQL (AWS RDS)

1. Create RDS MySQL instance
2. Configure security groups
3. Update backend connection string
```properties
spring.datasource.url=jdbc:mysql://your-rds-endpoint:3306/logistics_db
```

### Option B: Docker MySQL

```bash
docker run -d \
  --name mysql-db \
  -e MYSQL_ROOT_PASSWORD=yourpassword \
  -e MYSQL_DATABASE=logistics_db \
  -p 3306:3306 \
  -v mysql-data:/var/lib/mysql \
  mysql:8.0
```

## 4. Complete Docker Compose Setup

### docker-compose.yml
```yaml
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: rootpassword
      MYSQL_DATABASE: logistics_db
    ports:
      - "3306:3306"
    volumes:
      - mysql-data:/var/lib/mysql
    networks:
      - bluelock-network

  backend:
    build: ./backend
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/logistics_db
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: rootpassword
    depends_on:
      - mysql
    networks:
      - bluelock-network

  frontend:
    build: ./frontend
    ports:
      - "80:80"
    depends_on:
      - backend
    networks:
      - bluelock-network

volumes:
  mysql-data:

networks:
  bluelock-network:
    driver: bridge
```

### Deploy with Docker Compose
```bash
docker-compose up -d
```

## 5. SSL/HTTPS Configuration

### Using Let's Encrypt (Certbot)

#### Install Certbot
```bash
sudo apt install certbot python3-certbot-nginx
```

#### Obtain Certificate
```bash
sudo certbot --nginx -d yourdomain.com -d www.yourdomain.com
```

#### Auto-renewal
```bash
sudo certbot renew --dry-run
```

### Update Nginx for HTTPS
```nginx
server {
    listen 443 ssl http2;
    server_name yourdomain.com;
    
    ssl_certificate /etc/letsencrypt/live/yourdomain.com/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/yourdomain.com/privkey.pem;
    
    # ... rest of configuration
}

server {
    listen 80;
    server_name yourdomain.com;
    return 301 https://$server_name$request_uri;
}
```

## 6. Environment Variables

### Backend (.env or system variables)
```bash
export DATABASE_URL=jdbc:mysql://localhost:3306/logistics_db
export DB_USERNAME=root
export DB_PASSWORD=yourpassword
export JWT_SECRET=your-very-long-secret-key
export FRONTEND_URL=https://yourdomain.com
```

### Frontend (.env.production)
```env
REACT_APP_API_URL=https://api.yourdomain.com/api
```

## 7. Monitoring & Logging

### Backend Logging
Configure in `application-prod.properties`:
```properties
logging.level.root=INFO
logging.file.name=logs/application.log
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
```

### Frontend Error Tracking
Add error boundary and logging service (e.g., Sentry)

### Server Monitoring
- Use PM2 for Node.js process management
- Use systemd for Java application
- Set up health check endpoints

## 8. Performance Optimization

### Backend
- Enable caching
- Configure connection pooling
- Optimize database queries
- Enable GZIP compression

### Frontend
- Enable code splitting
- Lazy load routes
- Optimize images
- Enable service worker
- Configure CDN

### Nginx Optimization
```nginx
# Enable GZIP
gzip on;
gzip_types text/plain text/css application/json application/javascript;

# Enable caching
location ~* \.(jpg|jpeg|png|gif|ico|css|js)$ {
    expires 1y;
    add_header Cache-Control "public, immutable";
}
```

## 9. Backup Strategy

### Database Backup
```bash
# Daily backup script
mysqldump -u root -p logistics_db > backup_$(date +%Y%m%d).sql

# Automated backup with cron
0 2 * * * /path/to/backup-script.sh
```

### Application Backup
- Version control (Git)
- Regular snapshots
- Offsite backups

## 10. CI/CD Pipeline

### GitHub Actions Example

#### .github/workflows/deploy.yml
```yaml
name: Deploy

on:
  push:
    branches: [ main ]

jobs:
  deploy-backend:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 17
        uses: actions/setup-java@v2
        with:
          java-version: '17'
      - name: Build with Maven
        run: cd backend && ./mvnw clean package
      - name: Deploy to server
        run: |
          # Your deployment commands

  deploy-frontend:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Setup Node
        uses: actions/setup-node@v2
        with:
          node-version: '18'
      - name: Build
        run: cd frontend && npm ci && npm run build
      - name: Deploy
        run: |
          # Your deployment commands
```

## 11. Post-Deployment Checklist

- [ ] Application accessible via domain
- [ ] HTTPS working correctly
- [ ] All API endpoints responding
- [ ] Database connections working
- [ ] Authentication functioning
- [ ] All CRUD operations working
- [ ] Charts and analytics loading
- [ ] Mobile responsive
- [ ] No console errors
- [ ] Performance acceptable
- [ ] Monitoring set up
- [ ] Backups configured
- [ ] SSL certificate valid

## 12. Rollback Plan

### Quick Rollback
```bash
# Backend
docker stop bluelock-backend
docker run previous-version

# Frontend
# Revert to previous build
cp -r build-backup/* /var/www/bluelock/
```

### Database Rollback
```bash
mysql -u root -p logistics_db < backup_previous.sql
```

## 13. Maintenance Mode

### Nginx Maintenance Page
```nginx
if (-f /var/www/maintenance.html) {
    return 503;
}

error_page 503 @maintenance;
location @maintenance {
    root /var/www;
    rewrite ^(.*)$ /maintenance.html break;
}
```

## 14. Scaling Considerations

### Horizontal Scaling
- Load balancer (Nginx/HAProxy)
- Multiple backend instances
- Database replication
- Redis for session management

### Vertical Scaling
- Increase server resources
- Optimize database
- Add caching layer

## Support & Troubleshooting

### Common Issues

**Issue**: CORS errors in production
**Solution**: Update CORS configuration in backend

**Issue**: API calls failing
**Solution**: Check API_BASE_URL in frontend

**Issue**: Database connection timeout
**Solution**: Check connection pool settings

---

**Deployment Complete! 🎉**

Your BlueLock Logistics application is now live and ready for production use!
